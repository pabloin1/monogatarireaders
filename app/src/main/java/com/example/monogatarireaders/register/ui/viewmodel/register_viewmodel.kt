package com.example.monogatarireaders.register.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel

class RegisterViewModel(app : Application) : AndroidViewModel(app) {
    var username = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
    var passwordVisible = mutableStateOf(false)
    var confirmPasswordVisible = mutableStateOf(false)
    var isRegisterButtonEnabled = mutableStateOf(false)

    fun isRegisterButtonEnabled () = username.value.isNotEmpty() &&
            email.value.isNotEmpty() &&
            password.value.length >= 6 &&
            password.value == confirmPassword.value
}