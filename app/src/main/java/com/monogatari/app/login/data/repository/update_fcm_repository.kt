package com.monogatari.app.login.data.repository

import com.monogatari.app.core.domain.constants.API_CONFIG
import com.monogatari.app.login.domain.dtos.UpdateFcmDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface UpdateFcmRepository {
    @PUT("${API_CONFIG.API_AUTH}fcm-token")
    suspend fun updateFcmToken(@Body token: UpdateFcmDTO): Response<Unit>
}