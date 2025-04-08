package com.monogatari.app.profile.domain.use_cases

import com.monogatari.app.profile.data.services.GetLibraryService
import com.monogatari.app.profile.domain.adapters.LibraryAdapter

class GetProfileInfoUseCase{
    private val _getLibraryService = GetLibraryService()

    suspend fun execute() : Result<LibraryAdapter> {
        return try {
            val libraryResult = _getLibraryService.getLibrary()
            libraryResult.fold(
                onSuccess = { library ->
                    val favoriteCount = library.size
                    val chaptersRead = library.sumOf{ it?.lastReadChapter ?: 0 }.toInt()
                    Result.success(
                        LibraryAdapter(
                            library = library,
                            libraryCount = favoriteCount,
                            chaptersRead = chaptersRead
                        )
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
}