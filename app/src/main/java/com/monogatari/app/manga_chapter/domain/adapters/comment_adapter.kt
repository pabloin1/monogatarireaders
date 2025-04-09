package com.monogatari.app.manga_chapter.domain.adapters

import com.monogatari.app.shared.domain.adapters.UserAdapter

data class CommentAdapter(
    val id: Long,
    val user: UserAdapter,
    val chapterId: Long,
    val content: String,
    val createdAt: String,
    val edited: Boolean
)