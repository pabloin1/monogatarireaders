package com.monogatari.app.manga_detail.data.repositories

import com.monogatari.app.core.domain.constants.API_CONFIG
import com.monogatari.app.manga_detail.domain.adapters.MangaDetailAdapter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MangaDetailRepository {
    @GET("${API_CONFIG.API_MANGA}{mangaId}")
    suspend fun getMangaDetail(
        @Path("mangaId") mangaId: Int,
    ): Response<MangaDetailAdapter>
}