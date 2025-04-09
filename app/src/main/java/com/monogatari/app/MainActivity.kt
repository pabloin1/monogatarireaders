package com.monogatari.app

import SyncMangaWorker
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.core.data.local.network.NetworkManager
import com.monogatari.app.core.data.states.viewmodels.ViewModelManagerProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        NetworkManager.init(this)
        RetroFitClient.init(this)
        val workRequest = PeriodicWorkRequestBuilder<SyncMangaWorker>(
            2, java.util.concurrent.TimeUnit.MINUTES
        ).build()
        Log.e("MainActivity", "Enqueuing SyncMangaWorker...")
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "syncMangaWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
        setContent {
            ViewModelManagerProvider(this){
                App()
            }
        }
    }
}