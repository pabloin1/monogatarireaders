package com.monogatari.app.core.data.local.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.monogatari.app.core.domain.constants.ROOM_CONSTANTS

@Entity(
    tableName = ROOM_CONSTANTS.MANGA_CHAPTER_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = MangaEntity::class,
            parentColumns = ["id"],
            childColumns = ["mangaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("mangaId")]
)
data class ChapterEntity(
    @PrimaryKey val id: Long,
    val mangaId: Long,
    val mangaTitle: String,
    val title: String,
    val chapterNumber: Long,
    val description: String,
    val publishDate: String,
    val viewCount: Long,
    val isRead: Boolean = false,
    val isDownloaded: Boolean = false,
    val localPath: String? = null
)