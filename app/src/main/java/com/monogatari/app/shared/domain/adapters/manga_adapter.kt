package com.monogatari.app.shared.domain.adapters

import java.time.LocalDate

data class MangaAdapter(
    val id: Long,
    val title: String,
    val description: String,
    val coverImageUrl: String,
    val creator: UserAdapter,
    val createdAt: LocalDate,
    val updatedAt: LocalDate,
    val completed: Boolean,
    val genres: List<String>,
    val viewCount: Long,
    val averageRating: Float,
    val ratingCount: Int,
    val chapterCount: Int,
    val inUserFavorites: Boolean
)