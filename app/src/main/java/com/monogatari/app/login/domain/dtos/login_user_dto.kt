package com.monogatari.app.login.domain.dtos

data class LoginUserDTO(
    val username: String,
    val password: String,
    val fcmToken : String
)