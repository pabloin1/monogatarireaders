package com.monogatari.app.core.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.monogatari.app.core.data.local.room.entities.ChapterPageEntity

@Dao
interface ChapterPageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pages: List<ChapterPageEntity>)

    @Query("SELECT * FROM chapter_pages WHERE chapterId = :chapterId ORDER BY pageIndex")
    suspend fun getPagesForChapter(chapterId: Long): List<ChapterPageEntity>

    @Query("UPDATE chapter_pages SET isDownloaded = :isDownloaded, localPath = :localPath WHERE id = :pageId")
    suspend fun updateDownloadStatus(pageId: Long, isDownloaded: Boolean, localPath: String?)

    @Query("DELETE FROM chapter_pages WHERE chapterId = :chapterId")
    suspend fun deletePagesForChapter(chapterId: Long)
}