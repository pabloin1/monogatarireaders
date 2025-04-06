package com.example.monogatarireaders.discover_manga.data.repositories

import com.example.monogatarireaders.R
import com.example.monogatarireaders.discover_manga.domain.adapters.MangaAdapter

class MangaRepository {

    fun getTrendingManga(): List<MangaAdapter> {
        return allManga.filter { it.isTrending }
    }

    fun getAllManga(): List<MangaAdapter> {
        return allManga
    }

    // Mock data
    private val allManga = listOf(
        MangaAdapter(
            id = 1,
            title = "Chainsaw Man",
            coverImageResId = R.drawable.tokyo_ghoul_presentation,
            author = "Tatsuki Fujimoto",
            genres = listOf("Action", "Horror", "Supernatural"),
            rating = 4.8f,
            isTrending = true
        ),
        MangaAdapter(
            id = 2,
            title = "Jujutsu Kaisen",
            coverImageResId = R.drawable.tokyo_ghoul_presentation,
            author = "Gege Akutami",
            genres = listOf("Action", "Supernatural", "School"),
            rating = 4.7f,
            isTrending = true
        ),
        MangaAdapter(
            id = 3,
            title = "Demon Slayer",
            coverImageResId = R.drawable.tokyo_ghoul_presentation,
            author = "Koyoharu Gotouge",
            genres = listOf("Action", "Supernatural", "Historical"),
            rating = 4.9f,
            isTrending = true
        ),
        MangaAdapter(
            id = 4,
            title = "One Piece",
            coverImageResId = R.drawable.tokyo_ghoul_presentation,
            author = "Eiichiro Oda",
            genres = listOf("Action", "Adventure", "Fantasy"),
            rating = 4.9f,
            isTrending = true
        ),
        MangaAdapter(
            id = 5,
            title = "My Hero Academia",
            coverImageResId = R.drawable.tokyo_ghoul_presentation,
            author = "Kohei Horikoshi",
            genres = listOf("Action", "Superhero", "School"),
            rating = 4.6f,
            isTrending = true
        ),
        MangaAdapter(
            id = 6,
            title = "Tokyo Revengers",
            coverImageResId = R.drawable.tokyo_ghoul_presentation,
            author = "Ken Wakui",
            genres = listOf("Action", "Drama", "Time Travel"),
            rating = 4.5f,
            isTrending = true
        )
    )
}