package com.monogatari.app.manga_chapter.domain.models

import com.monogatari.app.manga_chapter.domain.adapters.CommentAdapter

sealed class CommentChapterStatus {
    data object Idle : CommentChapterStatus()
    data object Loading : CommentChapterStatus()
    data class Success(val comments: List<CommentAdapter>) : CommentChapterStatus()
    data class Error(val message: String) : CommentChapterStatus()
}