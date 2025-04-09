package com.monogatari.app.router.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import com.monogatari.app.core.ui.theme.GlowingYellow
import com.monogatari.app.router.domain.models.NavBarItem

object NavigationData {
    val login = "login"
    val register = "register"
    val not_found_screen = "NOT_FOUND_SCREEN"
    val manga_detail = "detail"
    val manga_chapter = "chapter/manga"
    // Function to generate the detail route with a specific ID
    fun detailRoute(id: String): String = "$manga_detail/$id"
    fun chapterRoute(mangaId: String, chapterId : String): String = "$manga_chapter/$mangaId/chapter/$chapterId"
    val home = NavBarItem(
        Icons.Default.Home,
        "Home",
        "Home",
        GlowingYellow
    )
    val library = NavBarItem(
        Icons.AutoMirrored.Outlined.MenuBook,
        "Library",
        "Library",
        GlowingYellow
    )
    val discover = NavBarItem(
        Icons.Default.Search,
        "Discover",
        "Discover",
        GlowingYellow
    )
    val profile = NavBarItem(
        Icons.Default.Person,
        "Profile",
        "Profile",
        GlowingYellow
    )
    val routes = listOf(
        home,
        library,
        discover,
        profile
    )
}