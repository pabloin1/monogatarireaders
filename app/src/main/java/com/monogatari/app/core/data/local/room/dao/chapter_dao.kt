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

    @Query("SELECT * FROM chapters WHERE mangaId = :mangaId AND id != 0 ORDER BY chapterNumber DESC")
    suspend fun getChaptersByMangaId(mangaId: Long): List<ChapterEntity>

    @Query("SELECT * FROM chapters WHERE id = :chapterId")
    suspend fun getChapterById(chapterId: Long): ChapterEntity?

    @Query("SELECT COUNT(*) FROM chapters WHERE isRead = 1")
    suspend fun getReadChaptersCount(): Int

    @Query("UPDATE chapters SET isRead = :isRead WHERE id = :chapterId")
    suspend fun updateReadStatus(chapterId: Long, isRead: Boolean)

    @Query("UPDATE chapters SET isDownloaded = :isDownloaded, localPath = :localPath WHERE id = :chapterId")
    suspend fun updateDownloadStatus(chapterId: Long, isDownloaded: Boolean, localPath: String?)

    @Transaction
    @Query("SELECT * FROM chapters WHERE id = :chapterId")
    suspend fun getChapterWithPages(chapterId: Long): ChapterWithPages?

    @Query("SELECT * FROM chapters WHERE id = :chapterId")
    suspend fun getChapterById(chapterId: Int): ChapterEntity?

    @Query("SELECT id FROM chapters WHERE mangaId = :mangaId AND chapterNumber = :chapterNumber LIMIT 1")
    suspend fun getChapterIdByMangaAndNumber(mangaId: Int, chapterNumber: Int): Int?

    @Query("SELECT * FROM chapters")
    suspend fun getAllChapters(): List<ChapterEntity>
}