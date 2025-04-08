package com.monogatari.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import com.monogatari.app.core.data.local.shared_preferences.UserPreference
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class MyApp : Application() {
    companion object {
        const val NOTIFICATION_CHANNEL_ID = "notification_fcm_monogatari"
    }

    override fun onCreate() {
        super.onCreate()
        try {
            Firebase.messaging.getToken().addOnCompleteListener { task ->
                Log.d("TAG_APP", "Token task completada: ${task.isSuccessful}")
                if (!task.isSuccessful) {
                    Log.e("TAG_APP", "Error: ${task.exception?.message}")
                    return@addOnCompleteListener
                }
                val token = task.result
                Log.d("TAG_APP", "Token obtenido: $token")
                saveTokenToPreferences(token)
            }
            Log.d("TAG_APP", "Solicitud de token enviada")
        } catch (e: Exception) {
            Log.e("TAG_APP", "Error al solicitar token: ${e.message}")
        }
        createNotificationChannel()
    }

    private fun saveTokenToPreferences(token : String){
        val userPreferences = UserPreference(this)
        userPreferences.saveFbToken(token)
        Log.d("TAG_APP", "Token $token guardado en shared preferences exitosamente")
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Notificaciones de FCM",
                NotificationManager.IMPORTANCE_HIGH,
            )
            channel.description = "Estas notificaciones van a ser recibidas desde FCM"
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}