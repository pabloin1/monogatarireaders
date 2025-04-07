package com.example.monogatarireaders.core.data.api.interceptors

import com.example.monogatarireaders.core.data.local.shared_preferences.UserPreference
import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor(private val userPreference: UserPreference) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code == 401 || response.code == 500) {
            userPreference.clearAll()
        }

        return response
    }
}