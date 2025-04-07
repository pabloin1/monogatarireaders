package com.example.monogatarireaders.register.data.repository

import com.example.monogatarireaders.core.domain.constants.API_CONFIG
import com.example.monogatarireaders.register.domain.dtos.RegisterUserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterUserRepository {
    @POST("${API_CONFIG.API_AUTH}register")
    suspend fun registerUser(
        @Body registerUserDTO: RegisterUserDTO
    ) : Response<Unit>
}