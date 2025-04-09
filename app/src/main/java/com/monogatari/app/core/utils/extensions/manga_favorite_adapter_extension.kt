package com.monogatari.app.core.utils.extensions

import com.monogatari.app.core.data.local.room.entities.MangaFavoriteEntity
import com.monogatari.app.shared.domain.adapters.MangaFavoriteAdapter

fun MangaFavoriteAdapter.toEntity() = MangaFavoriteEntity(
    id = id,
    mangaId = manga.id,
    lastReadChapter = lastReadChapter
)