package com.monogatari.app.core.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.monogatari.app.core.domain.constants.ROOM_CONSTANTS

@Entity(tableName = ROOM_CONSTANTS.MANGA_CHAPTER_PAGE_TABLE)
data class ChapterPageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val chapterId: Long,
    val pageUrl: String,
    val pageIndex: Int,
    val localPath: String? = null,
    val isDownloaded: Boolean = false
)