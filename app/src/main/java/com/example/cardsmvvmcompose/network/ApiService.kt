package com.example.cardsmvvmcompose.network

import com.example.cardsmvvmcompose.data.TmData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("home")
    suspend fun fetchPage(): Response<TmData>

    companion object {
        const val BASE_URL = "https://private-8ce77c-tmobiletest.apiary-mock.com/test/"
    }
}