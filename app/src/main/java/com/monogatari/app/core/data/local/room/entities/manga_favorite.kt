package com.monogatari.app.core.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.monogatari.app.core.domain.constants.ROOM_CONSTANTS

@Entity(tableName = ROOM_CONSTANTS.MANGA_FAVORITE_TABLE)
data class MangaFavoriteEntity(
    @PrimaryKey val id: Long,
    val mangaId: Long,
    val lastReadChapter: Int?
)