package com.monogatari.app.login.data.repository

import com.monogatari.app.core.domain.constants.API_CONFIG
import com.monogatari.app.login.domain.adapters.LoginUserAdapter
import com.monogatari.app.login.domain.dtos.LoginUserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRepository {
    @POST("${API_CONFIG.API_AUTH}login")
    suspend fun loginUser(
        @Body loginUserDTO: LoginUserDTO
    ) : Response<LoginUserAdapter>
}