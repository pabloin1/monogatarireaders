package com.monogatari.app.register.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.register.data.repository.RegisterUserRepository
import com.monogatari.app.register.domain.dtos.RegisterUserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterUserService {
    private val _registerUserRepository =RetroFitClient.createService(RegisterUserRepository::class.java)

    suspend fun register(reqUser : RegisterUserDTO) : Result<String> {
        return withContext(Dispatchers.IO){
            try {
                val response = _registerUserRepository.registerUser(reqUser)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.success("User registered successfully")
                    } else {
                        Result.success("User registered successfully")
                    }
                } else {
                    Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}