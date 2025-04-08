package com.monogatari.app.login.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.login.data.repository.GetUserProfileRepository
import com.monogatari.app.login.domain.adapters.UserProfileAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserProfileService {
    private val _getUserProfileRepository = RetroFitClient.createService(GetUserProfileRepository::class.java)

    suspend fun getUser() : Result<UserProfileAdapter> {
        return withContext(Dispatchers.IO){
            try {
                val response = _getUserProfileRepository.getUserProfile()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.success(body)
                    } else {
                        Result.failure(Exception("Empty response body"))
                    }
                } else {
                    val resError = "Error: ${response.code()} - ${response.message()}"
                    Result.failure(Exception(resError))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}