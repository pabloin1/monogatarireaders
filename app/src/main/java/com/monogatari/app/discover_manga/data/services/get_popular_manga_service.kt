package com.monogatari.app.discover_manga.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.discover_manga.data.repositories.GetPopularMangasRepository
import com.monogatari.app.shared.domain.adapters.MangaAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPopularMangaService {
    private val _getPopularMangaRepository = RetroFitClient.createService(GetPopularMangasRepository::class.java)

    suspend fun getPopularMangas() : Result<List<MangaAdapter>> {
        return withContext(Dispatchers.IO){
            try {
                val response = _getPopularMangaRepository.getPopularMangas()
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