package com.monogatari.app.manga_chapter.domain.adapters

data class MangaChapterAdapter(
    val id: Long,
    val mangaId: Long,
    val mangaTitle: String,
    val title: String,
    val chapterNumber: Long,
    val description: String,
    val publishDate: String,
    val pages: List<String>,
    val viewCount: Long,
)