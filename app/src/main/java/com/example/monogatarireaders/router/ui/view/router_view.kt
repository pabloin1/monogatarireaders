package com.example.monogatarireaders.router.ui.view

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import com.example.monogatarireaders.discover_manga.ui.screens.DiscoverMangaScreen
import com.example.monogatarireaders.profile.ui.screens.ProfileScreen
import com.example.monogatarireaders.router.data.NavigationData
import com.example.monogatarireaders.router.data.states.LocalRouter
import com.example.monogatarireaders.router.data.states.currentRoute

@Composable
fun RouterView() {
    val currentRoute = LocalRouter.current

    Crossfade(
        targetState = currentRoute.currentRoute(),
        label = "RouterView"
    ) {
        when (it) {
            NavigationData.discover.label -> {
                DiscoverMangaScreen()
            }
            NavigationData.profile.label -> {
                ProfileScreen()
            }
            else -> {
                // Handle other routes or show a default screen
            }
        }
    }
}