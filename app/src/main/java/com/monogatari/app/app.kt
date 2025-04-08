package com.monogatari.app

import androidx.compose.runtime.Composable
import com.monogatari.app.core.ui.theme.MonogatarireadersTheme
import com.monogatari.app.router.data.NavigationData
import com.monogatari.app.router.data.states.RouterProvider
import com.monogatari.app.router.ui.view.RouterView

@Composable
fun App() {
    MonogatarireadersTheme {
        RouterProvider(NavigationData.login){
            RouterView()
        }
    }
}