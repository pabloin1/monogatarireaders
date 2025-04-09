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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(page: ChapterPageEntity)

    @Query("UPDATE chapter_pages SET isDownloaded = :isDownloaded, localPath = :localPath WHERE id = :pageId")
    suspend fun updateDownloadStatus(pageId: Long, isDownloaded: Boolean, localPath: String?)

    @Query("DELETE FROM chapter_pages WHERE chapterId = :chapterId")
    suspend fun deletePagesForChapter(chapterId: Long)

    @Query("UPDATE chapter_pages SET localPath = :localPath, isDownloaded = :isDownloaded WHERE chapterId = :chapterId AND pageIndex = :pageIndex")
    suspend fun updatePageLocalPath(chapterId: Int, pageIndex: Int, localPath: String, isDownloaded: Boolean)

    @Query("SELECT * FROM chapter_pages WHERE chapterId = :chapterId AND localPath IS NOT NULL ORDER BY pageIndex ASC")
    suspend fun getPagesByChapterId(chapterId: Int): List<ChapterPageEntity>

    @Query("DELETE FROM chapter_pages WHERE chapterId = :chapterId")
    suspend fun deletePagesByChapterId(chapterId: Int)

    @Query("SELECT * FROM chapter_pages WHERE chapterId = :chapterId AND pageIndex = :pageIndex LIMIT 1")
    suspend fun getPageByChapterIdAndIndex(chapterId: Int, pageIndex: Int): ChapterPageEntity?

    @Query("SELECT COUNT(*) FROM chapter_pages WHERE chapterId = :chapterId AND isDownloaded = 1")
    suspend fun getDownloadedPagesCount(chapterId: Int): Int
}