package com.monogatari.app.manga_chapter.data.services

import com.monogatari.app.core.data.api.RetroFitClient
import com.monogatari.app.manga_chapter.data.repositories.CreateCommentChapterRepository
import com.monogatari.app.manga_chapter.domain.dtos.CreateCommentDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateCommentChapterService {
    private val _createCommentRepository = RetroFitClient.createService(CreateCommentChapterRepository::class.java)

    suspend fun createComment(createCommentDTO: CreateCommentDTO): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val response = _createCommentRepository.createCommentChapter(createCommentDTO)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Result.success("Comment created successfully")
                    } else {
                        Result.success("Comment created successfully")
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