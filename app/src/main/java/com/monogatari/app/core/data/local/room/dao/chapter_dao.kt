package com.monogatari.app.core.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.monogatari.app.core.data.local.room.entities.ChapterEntity
import com.monogatari.app.core.data.local.room.relations.ChapterWithPages

@Dao
interface ChapterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chapter: ChapterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(chapters: List<ChapterEntity>)

    @Query("SELECT * FROM chapters WHERE mangaId = :mangaId ORDER BY chapterNumber DESC")
    suspend fun getChaptersByMangaId(mangaId: Long): List<ChapterEntity>

    @Query("SELECT * FROM chapters WHERE id = :chapterId")
    suspend fun getChapterById(chapterId: Long): ChapterEntity?

    @Query("SELECT COUNT(*) FROM chapters WHERE isRead = 1")
    suspend fun getReadChaptersCount(): Int

    @Query("SELECT * FROM chapters WHERE isDownloaded = 1")
    suspend fun getDownloadedChapters(): List<ChapterEntity>

    @Query("UPDATE chapters SET isRead = :isRead WHERE id = :chapterId")
    suspend fun updateReadStatus(chapterId: Long, isRead: Boolean)

    @Query("UPDATE chapters SET isDownloaded = :isDownloaded, localPath = :localPath WHERE id = :chapterId")
    suspend fun updateDownloadStatus(chapterId: Long, isDownloaded: Boolean, localPath: String?)

    @Transaction
    @Query("SELECT * FROM chapters WHERE id = :chapterId")
    suspend fun getChapterWithPages(chapterId: Long): ChapterWithPages?

    @Transaction
    @Query("SELECT * FROM chapters WHERE mangaId = :mangaId ORDER BY chapterNumber DESC")
    suspend fun getChaptersWithPagesByMangaId(mangaId: Long): List<ChapterWithPages>
}