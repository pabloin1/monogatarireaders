package com.monogatari.app.login.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.login.data.repository.LoginRepository
import com.monogatari.app.login.domain.adapters.LoginUserAdapter
import com.monogatari.app.login.domain.dtos.LoginUserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {
    private val _loginRepository = RetroFitClient.createService(LoginRepository::class.java)

    suspend fun login(reqUser : LoginUserDTO) : Result<LoginUserAdapter> {
        return withContext(Dispatchers.IO){
            try {
                val response = _loginRepository.loginUser(reqUser)
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