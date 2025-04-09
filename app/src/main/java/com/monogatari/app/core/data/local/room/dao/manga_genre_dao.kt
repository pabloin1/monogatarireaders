package com.monogatari.app.core.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.monogatari.app.core.data.local.room.entities.MangaGenreEntity

@Dao
interface MangaGenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(genres: List<MangaGenreEntity>)

    @Query("DELETE FROM manga_genres WHERE mangaId = :mangaId")
    suspend fun deleteGenresForManga(mangaId: Long)
}