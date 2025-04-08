package com.monogatari.app.login.domain.models

import com.monogatari.app.login.domain.adapters.UserProfileAdapter

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val response : UserProfileAdapter): LoginState()
    data class Error(val message: String) : LoginState()
}