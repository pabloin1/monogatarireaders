package com.monogatari.app.profile.domain.adapters

import com.monogatari.app.shared.domain.adapters.MangaFavoriteAdapter

data class LibraryAdapter(
    val library : List<MangaFavoriteAdapter>,
    val libraryCount : Int,
    val chaptersRead : Int
)