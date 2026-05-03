package com.example.cardsmvvmcompose.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardsmvvmcompose.data.CardState
import com.example.cardsmvvmcompose.domain.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class MainViewModel @Inject constructor(repository: MyRepository): ViewModel() {

    val cardState: StateFlow<CardState> = repository.fetchCards()
        .catch { e -> CardState.Error(e.message ?: "") }
        .map { cards -> CardState.Success(cards) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), CardState.Loading)
}