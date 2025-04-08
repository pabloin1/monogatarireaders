package com.monogatari.app.register.domain.models

sealed class RegisterState {
    data object Idle : RegisterState()
    data object Loading : RegisterState()
    data class Success(val response: String) : RegisterState()
    data class Error(val message: String) : RegisterState()
}