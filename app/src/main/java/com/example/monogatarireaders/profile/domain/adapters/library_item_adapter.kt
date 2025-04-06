package com.example.monogatarireaders.profile.domain.adapters

data class LibraryItemAdapter(
    val id: Int,
    val title: String,
    val coverResourceId: Int,
    val chaptersRead: Int,
    val totalChapters: Int,
    val lastReadChapter: Int,
    val lastReadDaysAgo: Int,
    val isUpdated: Boolean
)