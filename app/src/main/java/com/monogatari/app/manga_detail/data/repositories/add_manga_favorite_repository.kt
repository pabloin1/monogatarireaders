package com.monogatari.app.manga_detail.data.repositories

import com.monogatari.app.core.domain.constants.API_CONFIG
import com.monogatari.app.manga_detail.domain.dtos.AddMangaFavoriteDto
import com.monogatari.app.shared.domain.adapters.MangaFavoriteAdapter
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AddMangaFavoriteRepository {
    @POST("${API_CONFIG.API_INTERACTION}favorite")
    suspend fun addMangaToFavorite(
        @Body() mangaFavoriteDto: AddMangaFavoriteDto
    ): Response<MangaFavoriteAdapter>
}