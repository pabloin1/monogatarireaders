package com.monogatari.app.manga_chapter.domain.adapters

data class MangaChapterAdapter(
    val id: Int,
    val mangaId: Int,
    val title: String,
    val chapterNumber: String,
    val images: List<Int>,
    val likes: Int,
    val views: Int,
    val comments: List<CommentAdapter>
)