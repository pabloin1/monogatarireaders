package com.monogatari.app.login.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.login.data.repository.UpdateFcmRepository
import com.monogatari.app.login.domain.dtos.UpdateFcmDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateFcmService {
    private val _updateFcmRepository = RetroFitClient.createService(UpdateFcmRepository::class.java)

    suspend fun updateFcm(reqFcm : UpdateFcmDTO) : Result<String> {
        return withContext(Dispatchers.IO){
            try {
                val response = _updateFcmRepository.updateFcmToken(reqFcm)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.success("FCM token updated successfully")
                    } else {
                        Result.success("FCM token updated successfully")
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