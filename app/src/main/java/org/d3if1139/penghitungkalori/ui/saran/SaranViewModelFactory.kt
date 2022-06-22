package org.d3if1139.penghitungkalori.ui.saran

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if1139.penghitungkalori.db.KaloriDao
import org.d3if1139.penghitungkalori.network.SaranApi
import org.d3if1139.penghitungkalori.ui.hitung.HitungViewModel
import java.lang.IllegalArgumentException

class SaranViewModelFactory (
    private val api: SaranApi
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SaranViewModel::class.java)) {
            return SaranViewModel(api) as T
        }
        throw IllegalArgumentException("Unkown ViewModel class")
    }
}