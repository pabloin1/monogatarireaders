package com.monogatari.app.login.domain.use_case

import android.app.Application
import com.monogatari.app.core.data.local.shared_preferences.UserPreference
import com.monogatari.app.login.data.services.GetUserProfileService
import com.monogatari.app.login.data.services.LoginService
import com.monogatari.app.login.data.services.UpdateFcmService
import com.monogatari.app.login.domain.adapters.UserProfileAdapter
import com.monogatari.app.login.domain.dtos.LoginUserDTO
import com.monogatari.app.login.domain.dtos.UpdateFcmDTO

class LoginUseCase(app : Application){
    private val _loginService = LoginService()
    private val _updateFcmService = UpdateFcmService()
    private val _getUserProfileService = GetUserProfileService()

    private val _userPreferences = UserPreference(app)

    suspend fun login(loginDto: LoginUserDTO): Result<UserProfileAdapter> {
        return try {
            val loginResult = _loginService.login(loginDto)

            loginResult.fold(
                onSuccess = { response ->
                    _userPreferences.saveToken(response.token)
                    val fcmToken = _userPreferences.getFbToken() ?: ""
                    val reqFcm = UpdateFcmDTO(fcmToken)

                    val fcmResult = _updateFcmService.updateFcm(reqFcm)
                    fcmResult.fold(
                        onSuccess = {
                            val userResult = _getUserProfileService.getUser()
                            userResult.fold(
                                onSuccess = { user ->
                                    _userPreferences.saveUserName(user.username)
                                    _userPreferences.saveUserId(user.id)
                                    Result.success(user)
                                },
                                onFailure = { error ->
                                    Result.failure(Exception(error.message))
                                }
                            )
                        },
                        onFailure = { error ->
                            Result.failure(Exception(error.message))
                        }
                    )
                },
                onFailure = { error ->
                    Result.failure(Exception(error.message))
                }
            )
        } catch (e: Exception) {
            Result.failure(Exception(e.message))
        }
    }
}