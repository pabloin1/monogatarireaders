package com.monogatari.app.core.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.monogatari.app.core.data.local.room.entities.MangaEntity
import com.monogatari.app.core.data.local.room.relations.MangaWithDetails

@Dao
interface MangaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(manga: MangaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mangas: List<MangaEntity>)

    @Query("SELECT * FROM mangas WHERE id = :mangaId")
    suspend fun getMangaById(mangaId: Long): MangaEntity?

    @Query("UPDATE mangas SET inUserFavorites = :isFavorite WHERE id = :mangaId")
    suspend fun updateFavoriteStatus(mangaId: Long, isFavorite: Boolean)

    @Transaction
    @Query("SELECT * FROM mangas WHERE id = :mangaId")
    suspend fun getMangaWithDetails(mangaId: Long): MangaWithDetails?

}