package com.example.monogatarireaders.login.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel

class LoginViewModel(app : Application) : AndroidViewModel(app) {
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var passwordVisible = mutableStateOf(false)
    var isLoginButtonEnabled = mutableStateOf(false)

    fun isLoginButtonEnabled () = email.value.isNotEmpty() && password.value.length >= 6

}