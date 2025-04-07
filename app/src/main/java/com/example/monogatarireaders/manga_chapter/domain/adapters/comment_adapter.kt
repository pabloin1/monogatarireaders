package com.example.monogatarireaders.manga_chapter.domain.adapters

data class CommentAdapter(
    val id: Int,
    val userName: String,
    val userAvatar: Int,
    val content: String,
    val timeAgo: String,
    val likes: Int,
    val isLiked: Boolean
)