package com.fauzangifari.domain.common

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

fun <T> Resource<T>.toUiState(): UiState<T> {
    return when (this) {
        is Resource.Loading -> UiState.Loading
        is Resource.Success -> UiState.Success(this.data!!)
        is Resource.Error -> UiState.Error(this.message ?: "Terjadi kesalahan")
    }
}
