package com.monogatari.app.shared.domain.adapters

import java.time.LocalDate

data class MangaFavoriteAdapter(
    val id: Long,
    val manga: MangaAdapter,
    val addedAt: LocalDate,
    val lastReadChapter: Int?
)