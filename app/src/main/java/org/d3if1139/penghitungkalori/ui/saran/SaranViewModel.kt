package org.d3if1139.penghitungkalori.ui.saran

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if1139.penghitungkalori.MainActivity
import org.d3if1139.penghitungkalori.model.SaranItem

import org.d3if1139.penghitungkalori.network.ApiStatus
import org.d3if1139.penghitungkalori.network.SaranApi
import org.d3if1139.penghitungkalori.network.UpdateWorker
import java.util.concurrent.TimeUnit

class SaranViewModel(private val api: SaranApi) : ViewModel(){
    var data = MutableLiveData<ArrayList<SaranItem>>()
    var status = MutableLiveData<ApiStatus>()
    init {
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try{
                val response = api.service.getSaran()
                if(response.isSuccessful){
                    data.postValue(response.body() as ArrayList<SaranItem>?)
                    Log.d("retrieveData: ", api.service.getSaran().body().toString())
                    status.postValue(ApiStatus.SUCCES)
                }
            }catch (e:Exception){
                status.postValue(ApiStatus.FAILED)
            }
        }
    }
    fun getData(): LiveData<ArrayList<SaranItem>> = data
    fun getStatus(): LiveData<ApiStatus> = status

}