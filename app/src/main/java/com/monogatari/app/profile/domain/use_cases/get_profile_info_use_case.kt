package com.monogatari.app.profile.domain.use_cases

import android.content.Context
import com.monogatari.app.core.data.local.room.database.MangaDatabase
import com.monogatari.app.core.utils.extensions.toEntity
import com.monogatari.app.core.utils.extensions.toGenreEntities
import com.monogatari.app.profile.data.services.GetLibraryService
import com.monogatari.app.profile.domain.adapters.LibraryAdapter

class GetProfileInfoUseCase(context : Context) {
    private val mangaDao = MangaDatabase.getDatabase(context).mangaDao()
    private val mangaGenreDao = MangaDatabase.getDatabase(context).mangaGenreDao()
    private val mangaFavoriteDao = MangaDatabase.getDatabase(context).mangaFavoriteDao()
    private val userDao = MangaDatabase.getDatabase(context).userDao()
    private val _getLibraryService = GetLibraryService()

    suspend fun execute() : Result<LibraryAdapter> {
        return try {
            val libraryResult = _getLibraryService.getLibrary()
            libraryResult.fold(
                onSuccess = { library ->
                    val favoriteCount = library.size
                    val chaptersRead = library.sumOf{ it?.lastReadChapter ?: 0 }.toInt()
                    val libraryAdapter = LibraryAdapter(
                        library = library,
                        libraryCount = favoriteCount,
                        chaptersRead = chaptersRead
                    )
                    syncLibrary(libraryAdapter)
                    Result.success(
                        libraryAdapter
                    )
                },
                onFailure = { error ->
                    Result.failure(Exception(error.message))
                }
            )
        } catch (e: Exception) {
            Result.failure(Exception(e.message))
        }
    }

    private suspend fun syncLibrary(libraryResponse : LibraryAdapter) {
        try {
            val favorites = libraryResponse.library.map { it.toEntity() }
            mangaFavoriteDao.insertAll(favorites)

            for (favorite in libraryResponse.library) {
                val manga = favorite.manga

                userDao.insert(manga.creator.toEntity())

                mangaDao.insert(manga.toEntity())

                val genreEntities = manga.toGenreEntities()
                mangaGenreDao.deleteGenresForManga(manga.id)
                mangaGenreDao.insertAll(genreEntities)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}