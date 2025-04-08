package com.monogatari.app.core.data.api

import android.content.Context
import com.monogatari.app.core.data.api.interceptors.AuthInterceptor
import com.monogatari.app.core.data.api.interceptors.ResponseInterceptor
import com.monogatari.app.core.data.local.shared_preferences.UserPreference
import com.monogatari.app.core.domain.constants.API_CONFIG
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroFitClient {
    private lateinit var userPreference: UserPreference

    fun init(ctx : Context){
        userPreference = UserPreference(ctx)
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(userPreference))
            .addInterceptor(ResponseInterceptor(userPreference))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(API_CONFIG.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }

    val getRetroFitClient: Retrofit get() = retrofit
}