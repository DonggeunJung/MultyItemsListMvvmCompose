package com.example.cardsmvvmcompose.data

sealed class CardState(open val cards: List<TmCard> = emptyList()) {
    data object Loading: CardState()
    data class Success(override val cards: List<TmCard>): CardState()
    data class Error(val msg: String): CardState()
}