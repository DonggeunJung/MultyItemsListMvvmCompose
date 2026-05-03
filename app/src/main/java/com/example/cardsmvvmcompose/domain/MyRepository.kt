package com.example.cardsmvvmcompose.domain

import com.example.cardsmvvmcompose.data.TmCard
import com.example.cardsmvvmcompose.network.ApiService
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MyRepository @Inject constructor(val apiService: ApiService) {
    fun fetchCards(): Flow<List<TmCard>> = flow {
        try {
            val cashed = apiService.fetchPage()
            if(cashed.isSuccessful) {
                cashed.body()?.let {
                    emit(it.page.cards)
                    return@flow
                }
            }
            throw Exception("Error fetching cards: Network error!")
        } catch(e: Exception) {
            throw Exception("Error fetching cards: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)
}