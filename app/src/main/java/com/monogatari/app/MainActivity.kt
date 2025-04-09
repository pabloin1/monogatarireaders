package com.monogatari.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.core.data.local.network.NetworkManager
import com.monogatari.app.core.data.states.viewmodels.ViewModelManagerProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        NetworkManager.init(this)
        RetroFitClient.init(this)
        setContent {
            ViewModelManagerProvider(this){
                App()
            }
        }
    }
}