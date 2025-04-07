package com.example.monogatarireaders.core.data.local.shared_preferences

import android.content.Context
import com.example.monogatarireaders.core.domain.constants.APP_PREFS

class UserPreference(context : Context){
    private val sharedPreferences = context.getSharedPreferences(APP_PREFS.PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun saveUserId(userId: Int) {
        sharedPreferences.edit().putString(APP_PREFS.USER_ID_PREFERENCE, userId.toString()).apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString(APP_PREFS.USER_ID_PREFERENCE, null)
    }

    fun clearUserId() {
        sharedPreferences.edit().remove(APP_PREFS.USER_ID_PREFERENCE).apply()
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(APP_PREFS.TOKEN_PREFERENCE, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(APP_PREFS.TOKEN_PREFERENCE, null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove(APP_PREFS.TOKEN_PREFERENCE).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}