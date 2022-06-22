package org.d3if1139.penghitungkalori.network

import com.google.gson.GsonBuilder
import org.d3if1139.penghitungkalori.model.Saran
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/rifkirizkia/Assessment3/main/"

private val gson= GsonBuilder()
    .setLenient()
    .create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

interface SaranApiService {
    @GET("saran.json")
    suspend fun getSaran(): Response<Saran>
}

object SaranApi{
    val service: SaranApiService by lazy {
        retrofit.create(SaranApiService::class.java)
    }
    fun getSaranUrl(nama: String): String{
        return "$BASE_URL$nama.jpg"
    }
}
enum class ApiStatus {LOADING, SUCCES, FAILED}