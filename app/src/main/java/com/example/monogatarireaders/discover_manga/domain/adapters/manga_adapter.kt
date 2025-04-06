package com.example.monogatarireaders.discover_manga.domain.adapters

data class MangaAdapter(
    val id: String,
    val title: String,
    val coverImageResId: Int,
    val author: String,
    val genres: List<String>,
    val rating: Float,
    val isTrending: Boolean = false
)