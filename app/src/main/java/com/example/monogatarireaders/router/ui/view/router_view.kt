package com.example.monogatarireaders.router.ui.view

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.monogatarireaders.discover_manga.ui.screens.DiscoverMangaScreen
import com.example.monogatarireaders.router.data.NavigationData

@Composable
fun RouterView() {
    val currentRoute = rememberSaveable{
        mutableStateOf(NavigationData.discover.label)
    }

    Crossfade(
        targetState = currentRoute.value,
        label = "RouterView"
    ) {
        when (it) {
            NavigationData.discover.label -> {
                DiscoverMangaScreen(currentRoute)
            }
            else -> {
                // Handle other routes or show a default screen
            }
        }
    }
}