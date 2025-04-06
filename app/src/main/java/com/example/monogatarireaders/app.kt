package com.example.monogatarireaders

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.monogatarireaders.core.ui.theme.MonogatarireadersTheme
import com.example.monogatarireaders.router.data.NavigationData
import com.example.monogatarireaders.router.data.states.RouterProvider
import com.example.monogatarireaders.router.ui.view.RouterView

@Composable
fun App() {
    MonogatarireadersTheme {
        RouterProvider(NavigationData.discover.label){
            RouterView()
        }
    }
}