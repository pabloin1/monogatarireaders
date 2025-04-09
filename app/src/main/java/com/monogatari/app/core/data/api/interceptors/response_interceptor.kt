package com.monogatari.app.core.data.api.interceptors

import com.monogatari.app.core.data.local.shared_preferences.UserPreference
import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor(private val userPreference: UserPreference) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val request = chain.request()

        val authorizedErrorsPath = listOf(
            "/interaction/favorite",
            "/interaction/read-progress"
        )

        val errorsPermission = authorizedErrorsPath.any { endpoint ->
            request.url.encodedPath.contains(endpoint)
        }

        if(errorsPermission){
            return response
        }

        if (response.code == 401 || response.code == 500) {
            userPreference.clearAll()
        }

        return response
    }
}