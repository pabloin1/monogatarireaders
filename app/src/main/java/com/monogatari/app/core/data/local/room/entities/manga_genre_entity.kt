package com.monogatari.app.core.data.local.room.entities

import androidx.room.Entity
import com.monogatari.app.core.domain.constants.ROOM_CONSTANTS

@Entity(tableName = ROOM_CONSTANTS.MANGA_GENRE_TABLE, primaryKeys = ["mangaId", "genre"])
data class MangaGenreEntity(
    val mangaId: Long,
    val genre: String
)