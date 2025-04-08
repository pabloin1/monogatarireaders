package com.monogatari.app.profile.data.repositories

import com.monogatari.app.R
import com.monogatari.app.profile.domain.adapters.LibraryItemAdapter

class LibraryRepository {
    fun getLibraryItems(): List<LibraryItemAdapter> {
        return libraryManga
    }

    // mock data
    private val libraryManga = listOf(
        LibraryItemAdapter(
            id = 1,
            title = "Chainsaw Man",
            coverResourceId = R.drawable.tokyo_ghoul_presentation,
            chaptersRead = 215,
            totalChapters = 245,
            lastReadChapter = 215,
            lastReadDaysAgo = 2,
            isUpdated = true
        ),
        LibraryItemAdapter(
            id = 2,
            title = "Jujutsu Kaisen",
            coverResourceId = R.drawable.tokyo_ghoul_presentation,
            chaptersRead = 120,
            totalChapters = 140,
            lastReadChapter = 120,
            lastReadDaysAgo = 5,
            isUpdated = false
        ),
        LibraryItemAdapter(
            id = 3,
            title = "Demon Slayer",
            coverResourceId = R.drawable.tokyo_ghoul_presentation,
            chaptersRead = 1085,
            totalChapters = 1098,
            lastReadChapter = 1085,
            lastReadDaysAgo = 1,
            isUpdated = true
        ),
        LibraryItemAdapter(
            id = 4,
            title = "One Piece",
            coverResourceId = R.drawable.tokyo_ghoul_presentation,
            chaptersRead = 143,
            totalChapters = 143,
            lastReadChapter = 143,
            lastReadDaysAgo = 30,
            isUpdated = false
        ),
        LibraryItemAdapter(
            id = 5,
            title = "My Hero Academia",
            coverResourceId = R.drawable.tokyo_ghoul_presentation,
            chaptersRead = 205,
            totalChapters = 205,
            lastReadChapter = 205,
            lastReadDaysAgo = 14,
            isUpdated = false
        ),
        LibraryItemAdapter(
            id = 6,
            title = "Tokyo Revengers",
            coverResourceId = R.drawable.tokyo_ghoul_presentation,
            chaptersRead = 205,
            totalChapters = 290,
            lastReadChapter = 205,
            lastReadDaysAgo = 14,
            isUpdated = false
        )
    )
}