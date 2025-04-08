package com.monogatari.app.login.domain.models

import com.monogatari.app.login.domain.adapters.LoginUserAdapter

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val response : LoginUserAdapter): LoginState()
    data class Error(val message: String) : LoginState()
}