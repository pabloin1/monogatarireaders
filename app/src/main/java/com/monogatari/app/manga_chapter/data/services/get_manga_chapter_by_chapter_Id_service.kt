package com.monogatari.app.manga_chapter.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.manga_chapter.data.repositories.GetMangaChapterByChapterIdRepository
import com.monogatari.app.manga_chapter.domain.adapters.MangaChapterAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMangaChapterByChapterIdService {
    private val _getMangaChapterRepository = RetroFitClient.createService(
        GetMangaChapterByChapterIdRepository::class.java
    )

    suspend fun getMangaChapter(chapterId: Int): Result<MangaChapterAdapter> {
        return withContext(Dispatchers.IO) {
            try {
                val response = _getMangaChapterRepository.getMangaChapter(chapterId)
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