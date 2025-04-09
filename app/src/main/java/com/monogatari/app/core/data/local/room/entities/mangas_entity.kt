package com.monogatari.app.core.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.monogatari.app.core.domain.constants.ROOM_CONSTANTS

@Entity(tableName = ROOM_CONSTANTS.MANGA_TABLE)
data class MangaEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val description: String,
    val coverImageUrl: String,
    val creatorId: Long,
    val createdAt: String,
    val updatedAt: String,
    val completed: Boolean,
    val viewCount: Long,
    val averageRating: Float,
    val ratingCount: Int,
    val chapterCount: Int?,
    val inUserFavorites: Boolean,
    val lastUpdated: Long = System.currentTimeMillis()
)