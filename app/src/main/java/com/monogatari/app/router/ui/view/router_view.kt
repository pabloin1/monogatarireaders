package com.monogatari.app.router.ui.view

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.monogatari.app.core.data.local.shared_preferences.UserPreference
import com.monogatari.app.discover_manga.ui.screens.DiscoverMangaScreen
import com.monogatari.app.login.ui.screen.LoginScreen
import com.monogatari.app.manga_chapter.ui.screens.MangaChapterScreen
import com.monogatari.app.manga_detail.ui.screens.MangaDetailScreen
import com.monogatari.app.not_found.ui.screens.NotFoundScreen
import com.monogatari.app.profile.ui.screens.ProfileScreen
import com.monogatari.app.register.ui.screen.RegisterScreen
import com.monogatari.app.router.data.NavigationData
import com.monogatari.app.router.data.states.LocalRouter
import com.monogatari.app.router.data.states.currentRoute
import com.monogatari.app.router.data.states.navigateTo

@Composable
fun RouterView(){
    val currentRoute = LocalRouter.current
    val userPreference = UserPreference(LocalContext.current)
    val token = userPreference.getToken() ?: ""

    LaunchedEffect(token){
        if (token.isEmpty()) {
            currentRoute.navigateTo(NavigationData.login)
        }else{
            if (currentRoute.currentRoute() == NavigationData.login && token.isNotEmpty()) {
                currentRoute.navigateTo(NavigationData.discover.label)
            }
        }
    }

    Crossfade(
        targetState = currentRoute.currentRoute(),
        label = "RouterView"
    ) { route ->
        when {
            route == NavigationData.login -> {
                LoginScreen()
            }
            route == NavigationData.register -> {
                RegisterScreen()
            }
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