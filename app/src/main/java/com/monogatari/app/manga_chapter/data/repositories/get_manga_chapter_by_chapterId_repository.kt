package com.monogatari.app.manga_chapter.data.repositories

import com.monogatari.app.core.domain.constants.API_CONFIG
import com.monogatari.app.manga_chapter.domain.adapters.MangaChapterAdapter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GetMangaChapterByChapterIdRepository {
    @GET("${API_CONFIG.API_CHAPTER}{id}")
    suspend fun getMangaChapter(@Path("id") id: Int): Response<MangaChapterAdapter>
}