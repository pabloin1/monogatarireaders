package com.monogatari.app.manga_detail.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.manga_detail.data.repositories.MangaDetailRepository
import com.monogatari.app.manga_detail.domain.adapters.MangaDetailAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMangaDetailService {
    private val _mangaDetailRepository = RetroFitClient.createService(MangaDetailRepository::class.java)

    suspend fun getMangaDetail(mangaId: Int): Result<MangaDetailAdapter> {
        return withContext(Dispatchers.IO){
            try {
                val response = _mangaDetailRepository.getMangaDetail(mangaId)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.success(body)
                    } else {
                        Result.failure(Exception("Empty response body"))
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