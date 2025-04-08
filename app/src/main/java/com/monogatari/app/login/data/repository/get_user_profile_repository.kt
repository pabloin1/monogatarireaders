package com.monogatari.app.login.data.repository

import com.monogatari.app.core.domain.constants.API_CONFIG
import com.monogatari.app.login.domain.adapters.UserProfileAdapter
import retrofit2.Response
import retrofit2.http.GET

interface GetUserProfileRepository {
    @GET("${API_CONFIG.API_AUTH}profile")
    suspend fun getUserProfile(): Response<UserProfileAdapter>
}