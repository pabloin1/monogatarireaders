package com.monogatari.app.profile.domain.use_cases

import android.content.Context
import com.monogatari.app.core.data.local.room.database.MangaDatabase
import com.monogatari.app.profile.domain.adapters.LibraryAdapter

class GetProfileInfoLocalUseCase(context: Context){
    private val mangaFavoriteDao = MangaDatabase.getDatabase(context).mangaFavoriteDao()

    suspend fun execute() : LibraryAdapter{
        val favoritesWithDetails = mangaFavoriteDao.getAllFavoritesWithDetails()
        val library = favoritesWithDetails.map { it.toMangaFavoriteAdapter() }
        return LibraryAdapter(
            library = library,
            libraryCount = favoritesWithDetails.size,
            chaptersRead = library.sumOf{ it?.lastReadChapter ?: 0 }.toInt()
        )
    }
}
