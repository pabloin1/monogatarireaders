package com.example.monogatarireaders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.monogatarireaders.core.data.api.RetroFitClient
import com.example.monogatarireaders.core.data.states.viewmodels.ViewModelManagerProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        RetroFitClient.init(this)
        setContent {
            ViewModelManagerProvider(this){
                App()
            }
        }
    }
}