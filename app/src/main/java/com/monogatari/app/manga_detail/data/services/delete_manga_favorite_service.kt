package com.monogatari.app.manga_detail.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.manga_detail.data.repositories.DeleteMangaFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteMangaFavoriteService {
    private val _deleteMangaFavoriteRepository = RetroFitClient.createService(DeleteMangaFavoriteRepository::class.java)

    suspend fun deleteFavorite(mangaId: Int): Result<String> {
        return withContext(Dispatchers.IO){
            try {
                val response = _deleteMangaFavoriteRepository.deleteMangaFavorite(mangaId)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.success("Manga deleted from favorites successfully")
                    } else {
                        Result.success("Manga deleted from favorites successfully")
                    }
                } else {
                    val resError = "Error: ${response.code()} - ${response.message()}"
                    Result.failure(Exception(resError))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}