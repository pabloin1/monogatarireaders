package com.monogatari.app.manga_chapter.data.repositories

import com.monogatari.app.core.domain.constants.API_CONFIG
import com.monogatari.app.manga_chapter.domain.adapters.CommentAdapter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GetChaptersCommentRepository {
    @GET("${API_CONFIG.API_INTERACTION}comment/chapter/{chapterId}")
    suspend fun getChaptersComment(
        @Path("chapterId") chapterId: Int
    ): Response<List<CommentAdapter>>
}