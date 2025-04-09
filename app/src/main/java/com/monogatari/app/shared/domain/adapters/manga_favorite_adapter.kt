package com.monogatari.app.shared.domain.adapters

data class MangaFavoriteAdapter(
    val id: Long,
    val manga: MangaAdapter,
    val lastReadChapter: Int?
)