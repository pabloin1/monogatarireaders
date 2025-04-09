package com.monogatari.app.manga_chapter.data.repositories

import com.monogatari.app.core.domain.constants.API_CONFIG
import com.monogatari.app.manga_chapter.domain.dtos.CreateCommentDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateCommentChapterRepository {
    @POST("${API_CONFIG.API_INTERACTION}comment")
    suspend fun createCommentChapter(
        @Body() createCommentDTO: CreateCommentDTO
    ): Response<Unit>
}