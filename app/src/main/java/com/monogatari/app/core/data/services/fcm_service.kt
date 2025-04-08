package com.monogatari.app.core.data.services
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.monogatari.app.MyApp
import com.monogatari.app.R
import com.monogatari.app.core.data.local.shared_preferences.UserPreference

class FcmService : FirebaseMessagingService() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("FCM_TAG", "onMessageReceived: ${message.notification?.title} ${message.notification?.body}")
        showNotification(message.notification)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        val userPreferences = UserPreference(applicationContext)
        userPreferences.clearFbToken()
        userPreferences.saveFbToken(token)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showNotification(message: RemoteMessage.Notification?) {
        val notificationManager = getSystemService(NotificationManager::class.java)
        val title = message?.title ?: "Monogatari"
        val body = message?.body ?: "You have a new message"
        val notification = NotificationCompat.Builder(this, MyApp.NOTIFICATION_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.monogatari_logo)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(1, notification)
    }
}