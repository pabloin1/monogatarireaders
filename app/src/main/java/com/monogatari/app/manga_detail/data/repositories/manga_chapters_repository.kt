package com.monogatari.app.manga_detail.data.repositories

import com.monogatari.app.core.domain.constants.API_CONFIG
import com.monogatari.app.manga_detail.domain.adapters.ChapterAdapter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MangaChaptersRepository {
    @GET("${API_CONFIG.API_CHAPTER}manga/{mangaId}")
    suspend fun getMangaChapters(
        @Path("mangaId") mangaId: Int
    ): Response<List<ChapterAdapter>>
}