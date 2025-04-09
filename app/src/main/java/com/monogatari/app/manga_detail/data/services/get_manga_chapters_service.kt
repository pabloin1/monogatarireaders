package com.monogatari.app.manga_detail.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.manga_detail.data.repositories.MangaChaptersRepository
import com.monogatari.app.manga_detail.domain.adapters.ChapterAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMangaChaptersService {
    private val _mangaChapterRepository = RetroFitClient.createService(MangaChaptersRepository::class.java)

    suspend fun getMangaChapters(mangaId: Int): Result<List<ChapterAdapter>> {
        return withContext(Dispatchers.IO){
            try {
                val response = _mangaChapterRepository.getMangaChapters(mangaId)
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