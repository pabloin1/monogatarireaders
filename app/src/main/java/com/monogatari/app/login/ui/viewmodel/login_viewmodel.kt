package com.monogatari.app.login.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.monogatari.app.core.data.local.shared_preferences.UserPreference
import com.monogatari.app.login.data.services.LoginService
import com.monogatari.app.login.domain.dtos.LoginUserDTO
import com.monogatari.app.login.domain.models.LoginState
import kotlinx.coroutines.launch

class LoginViewModel(app : Application) : AndroidViewModel(app) {
    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var passwordVisible = mutableStateOf(false)
    var isLoginButtonEnabled = mutableStateOf(false)
    var state = mutableStateOf<LoginState>(LoginState.Idle)

    private val _loginService = LoginService()
    private val _userPreferences = UserPreference(app)

    fun isLoginButtonEnabled () = username.value.isNotEmpty() && password.value.length >= 6

    fun login () {
        val fbToken = _userPreferences.getFbToken() ?: ""
        val loginDto = LoginUserDTO(
            username = username.value,
            password = password.value,
            fcmToken = fbToken
        )
        if (isLoginButtonEnabled()) {
            state.value = LoginState.Loading
            viewModelScope.launch {
                try {
                    _loginService.login(
                        loginDto
                    ).fold(
                        onSuccess = { response ->
                            _userPreferences.saveToken(response.token)
                            state.value = LoginState.Success(response)
                        },
                        onFailure = { error ->
                            val errorMessage = error.message
                            state.value = LoginState.Error(errorMessage ?: "Unknown error")
                        }
                    )
                }catch (e: Exception) {
                    state.value = LoginState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }
}