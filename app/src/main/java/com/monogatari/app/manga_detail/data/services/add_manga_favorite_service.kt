package com.monogatari.app.manga_detail.data.services

import android.util.Log
import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.manga_detail.data.repositories.AddMangaFavoriteRepository
import com.monogatari.app.manga_detail.domain.dtos.AddMangaFavoriteDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddMangaFavoriteService {
    private val _addMangaFavoriteRepository = RetroFitClient.createService(AddMangaFavoriteRepository::class.java)

    suspend fun addFavorite(favoriteDto: AddMangaFavoriteDto): Result<String> {
        return withContext(Dispatchers.IO){
            try {
                val response = _addMangaFavoriteRepository.addMangaToFavorite(favoriteDto)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.success("Manga added to favorites successfully")
                    } else {
                        Result.failure(Exception("Empty response body"))
                    }
                } else if(response.code() == 500) {
                    Log.d("ADD_SERVICE_TAG", "addFavorite: ${response.body()}")
                    Result.success("Manga added to favorites successfully")
                }else{
                    val resError = "Error: ${response.code()} - ${response.message()}"
                    Result.failure(Exception(resError))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}