package com.monogatari.app.login.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.monogatari.app.core.data.local.shared_preferences.UserPreference
import com.monogatari.app.login.data.services.LoginService
import com.monogatari.app.login.domain.dtos.LoginUserDTO
import com.monogatari.app.login.domain.models.LoginState
import com.monogatari.app.login.domain.use_case.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(app : Application) : AndroidViewModel(app) {
    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var passwordVisible = mutableStateOf(false)
    var isLoginButtonEnabled = mutableStateOf(false)
    var state = mutableStateOf<LoginState>(LoginState.Idle)

    private val _loginUseCase = LoginUseCase(app)

    fun isLoginButtonEnabled () = username.value.isNotEmpty() && password.value.length >= 6

    fun login() {
        viewModelScope.launch {
            try {
                state.value = LoginState.Loading
                val loginDto = LoginUserDTO(
                    username = username.value,
                    password = password.value,
                    fcmToken = UserPreference(getApplication()).getFbToken() ?: ""
                )
                val result = _loginUseCase.login(loginDto)
                result.fold(
                    onSuccess = { user ->
                        state.value = LoginState.Success(user)
                        Log.d("LOGIN_VM_TAG", "login: ${user.username}")
                    },
                    onFailure = { error ->
                        state.value = LoginState.Error(error.message.toString())
                    }
                )
            } catch (e: Exception) {
                state.value = LoginState.Error(e.message.toString())
            }
        }
    }
}