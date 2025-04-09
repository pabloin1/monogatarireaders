package com.monogatari.app.manga_chapter.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.manga_chapter.data.repositories.GetChaptersCommentRepository
import com.monogatari.app.manga_chapter.domain.adapters.CommentAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetChapterCommentsService {
    private val _getChapterCommentRepository = RetroFitClient.createService(GetChaptersCommentRepository::class.java)

    suspend fun getChapterComments(chapterId: Int): Result<List<CommentAdapter>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = _getChapterCommentRepository.getChaptersComment(chapterId)
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