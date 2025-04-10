package com.monogatari.app.register.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.monogatari.app.core.data.local.shared_preferences.UserPreference
import com.monogatari.app.register.data.services.RegisterUserService
import com.monogatari.app.register.domain.dtos.RegisterUserDTO
import com.monogatari.app.register.domain.models.RegisterState
import kotlinx.coroutines.launch

class RegisterViewModel(app : Application) : AndroidViewModel(app) {
    var username = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
    var passwordVisible = mutableStateOf(false)
    var confirmPasswordVisible = mutableStateOf(false)
    var isRegisterButtonEnabled = mutableStateOf(false)
    var state = mutableStateOf<RegisterState>(RegisterState.Idle)

    private val _registerUserService = RegisterUserService()

    fun isRegisterButtonEnabled () = username.value.isNotEmpty() &&
            email.value.isNotEmpty() &&
            password.value.length >= 6 &&
            password.value == confirmPassword.value

    fun registerUser() {
        val userDto = RegisterUserDTO(
            username = username.value,
            email = email.value,
            password = password.value,
            displayName = username.value,
            fcmToken = UserPreference(getApplication()).getFbToken() ?: ""
        )
        if (isRegisterButtonEnabled()) {
            state.value = RegisterState.Loading
            viewModelScope.launch {
                try {
                    _registerUserService.register(
                        userDto
                    ).fold(
                        onSuccess = { response ->
                            state.value = RegisterState.Success(response)
                        },
                        onFailure = { error ->
                            val errorMessage = error.message
                            state.value = RegisterState.Error(errorMessage ?: "Unknown error")
                        }
                    )
                }catch (e: Exception) {
                    state.value = RegisterState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }
}