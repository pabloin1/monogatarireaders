package com.monogatari.app.profile.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.profile.data.repositories.LibraryRepository
import com.monogatari.app.shared.domain.adapters.MangaFavoriteAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetLibraryService {
    private val _libraryRepository = RetroFitClient.createService(LibraryRepository::class.java)

    suspend fun getLibrary() : Result<List<MangaFavoriteAdapter>> {
        return withContext(Dispatchers.IO){
            try {
                val response = _libraryRepository.getLibraryItems()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.success(body)
                    } else {
                        Result.success(emptyList())
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