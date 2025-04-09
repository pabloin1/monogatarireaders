package com.monogatari.app.manga_detail.domain.adapters

import com.monogatari.app.shared.domain.adapters.CreatorAdapter

data class MangaDetailAdapter(
    val averageRating: Float,
    val chapterCount: Int,
    val completed: Boolean,
    val coverImageUrl: String,
    val createdAt: String,
    val creator: CreatorAdapter,
    val description: String,
    val genres: List<String>,
    val id: Long,
    var inUserFavorites: Boolean,
    val ratingCount: Int,
    val title: String,
    val updatedAt: String,
    val viewCount: Int
)