package com.monogatari.app.manga_chapter.domain.models

import com.monogatari.app.manga_chapter.domain.adapters.MangaChapterAdapter

sealed class MangaChapterStatus {
    data object Idle : MangaChapterStatus()
    data object Loading : MangaChapterStatus()
    data class Success(val chapters : MangaChapterAdapter) : MangaChapterStatus()
    data class Error(val message : String) : MangaChapterStatus()
}