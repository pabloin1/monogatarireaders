package com.monogatari.app.core.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.monogatari.app.core.data.local.room.entities.MangaFavoriteEntity
import com.monogatari.app.core.data.local.room.relations.MangaFavoriteWithDetails

@Dao
interface MangaFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: MangaFavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(favorites: List<MangaFavoriteEntity>)

    @Query("SELECT * FROM manga_favorites WHERE mangaId = :mangaId")
    suspend fun getFavoriteByMangaId(mangaId: Long): MangaFavoriteEntity?

    @Query("UPDATE manga_favorites SET lastReadChapter = :chapterNumber WHERE mangaId = :mangaId")
    suspend fun updateLastReadChapter(mangaId: Long, chapterNumber: Int)

    @Transaction
    @Query("SELECT * FROM manga_favorites")
    suspend fun getAllFavoritesWithDetails(): List<MangaFavoriteWithDetails>

    @Transaction
    @Query("SELECT * FROM manga_favorites WHERE mangaId = :mangaId")
    suspend fun getFavoriteWithDetailsByMangaId(mangaId: Long): MangaFavoriteWithDetails?
}