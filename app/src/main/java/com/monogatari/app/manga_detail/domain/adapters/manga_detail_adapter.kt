package com.monogatari.app.manga_detail.domain.adapters

data class MangaDetailAdapter(
    val id: Int,
    val title: String,
    val coverImageResId: Int,
    val author: String,
    val genres: List<String>,
    val rating: Float,
    val status: String,
    val synopsis: String,
    val chapters: List<ChapterAdapter>,
    val isTrending: Boolean = false
)