package com.monogatari.app.manga_detail.data.repositories

import com.monogatari.app.core.domain.constants.API_CONFIG
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Path

interface DeleteMangaFavoriteRepository {
    @DELETE("${API_CONFIG.API_INTERACTION}favorite/{mangaId}")
    suspend fun deleteMangaFavorite(
        @Path("mangaId") mangaId : Int
    ): Response<Unit>
}