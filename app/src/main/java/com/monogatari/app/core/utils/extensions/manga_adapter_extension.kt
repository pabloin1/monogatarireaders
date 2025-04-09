package com.monogatari.app.core.utils.extensions

import com.monogatari.app.core.data.local.room.entities.MangaEntity
import com.monogatari.app.core.data.local.room.entities.MangaGenreEntity
import com.monogatari.app.shared.domain.adapters.MangaAdapter

fun MangaAdapter.toEntity() = MangaEntity(
    id = id,
    title = title,
    description = description,
    coverImageUrl = coverImageUrl,
    creatorId = creator.id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    completed = completed,
    viewCount = viewCount,
    averageRating = averageRating,
    ratingCount = ratingCount,
    chapterCount = chapterCount,
    inUserFavorites = inUserFavorites
)

fun MangaAdapter.toGenreEntities(): List<MangaGenreEntity> =
    genres.map { genre -> MangaGenreEntity(mangaId = id, genre = genre) }