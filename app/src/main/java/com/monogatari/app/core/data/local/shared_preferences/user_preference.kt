package com.monogatari.app.core.data.local.shared_preferences

import android.content.Context
import com.monogatari.app.core.domain.constants.APP_PREFS

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

    fun saveFbToken(token: String) {
        sharedPreferences.edit().putString(APP_PREFS.FB_TOKEN_PREFERENCE, token).apply()
    }

    fun getFbToken(): String? {
        return sharedPreferences.getString(APP_PREFS.FB_TOKEN_PREFERENCE, null)
    }

    fun clearFbToken() {
        sharedPreferences.edit().remove(APP_PREFS.FB_TOKEN_PREFERENCE).apply()
    }

    fun saveUserName(userName: String) {
        sharedPreferences.edit().putString(APP_PREFS.USER_NAME_PREFERENCE, userName).apply()
    }

    fun getUserName(): String? {
        return sharedPreferences.getString(APP_PREFS.USER_NAME_PREFERENCE, null)
    }

    fun clearUserName() {
        sharedPreferences.edit().remove(APP_PREFS.USER_NAME_PREFERENCE).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}