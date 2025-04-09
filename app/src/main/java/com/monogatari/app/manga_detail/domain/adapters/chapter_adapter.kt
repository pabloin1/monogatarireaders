package com.monogatari.app.manga_detail.domain.adapters

data class ChapterAdapter(
    val chapterNumber: Int,
    val id: Int,
    val mangaId: Int,
    val publishDate: String,
    val title: String,
    val viewCount: Int
)