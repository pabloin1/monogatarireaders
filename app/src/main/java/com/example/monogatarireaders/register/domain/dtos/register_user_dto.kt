package com.example.monogatarireaders.register.domain.dtos

data class RegisterUserDTO(
    val username: String,
    val password: String,
    val rol: String = "READER",
    val email: String,
    val displayName: String
)