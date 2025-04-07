package com.example.monogatarireaders.router.ui.view

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import com.example.monogatarireaders.discover_manga.ui.screens.DiscoverMangaScreen
import com.example.monogatarireaders.manga_chapter.ui.screens.MangaChapterScreen
import com.example.monogatarireaders.manga_chapter.ui.viewmodel.MangaChapterViewmodel
import com.example.monogatarireaders.manga_detail.ui.screens.MangaDetailScreen
import com.example.monogatarireaders.not_found.ui.screens.NotFoundScreen
import com.example.monogatarireaders.profile.ui.screens.ProfileScreen
import com.example.monogatarireaders.router.data.NavigationData
import com.example.monogatarireaders.router.data.states.LocalRouter
import com.example.monogatarireaders.router.data.states.currentRoute
import com.example.monogatarireaders.router.data.states.navigateTo

@Composable
fun RouterView(){
    val currentRoute = LocalRouter.current

    Crossfade(
        targetState = currentRoute.currentRoute(),
        label = "RouterView"
    ) { route ->
        when {
            route == NavigationData.discover.label -> {
                DiscoverMangaScreen()
            }
            route == NavigationData.profile.label -> {
                ProfileScreen()
            }
            route.startsWith("${NavigationData.manga_detail}/") -> {
                val id = route.substringAfter("${NavigationData.manga_detail}/")
                MangaDetailScreen(mangaId = id.toInt())
            }
            route == NavigationData.manga_chapter -> {
                MangaChapterScreen()
            }
            else -> {
                NotFoundScreen(
                    onBackClicked = {
                        currentRoute.navigateTo(NavigationData.discover.label)
                    }
                )
            }
        }
    }
}