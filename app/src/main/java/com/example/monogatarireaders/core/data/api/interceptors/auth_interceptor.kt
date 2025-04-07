package com.example.monogatarireaders.core.data.api.interceptors

import com.example.monogatarireaders.core.data.local.shared_preferences.UserPreference
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val userPreference: UserPreference) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val nonProtectedEndpoints = listOf(
            "/auth/login",
            "/auth/register",
        )

        val notNeedsAuth = nonProtectedEndpoints.any { endpoint ->
            request.url.encodedPath.contains(endpoint)
        }

        return if (notNeedsAuth){
            chain.proceed(request)
        }else{
            val token = userPreference.getToken()
            if (!token.isNullOrEmpty()) {
                val authenticatedRequest = request.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(authenticatedRequest)
            } else {
                chain.proceed(request)
            }
        }
    }
}