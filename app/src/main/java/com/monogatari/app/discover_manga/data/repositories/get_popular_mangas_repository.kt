package com.monogatari.app.discover_manga.data.repositories

import com.monogatari.app.core.domain.constants.API_CONFIG
import com.monogatari.app.shared.domain.adapters.MangaAdapter
import retrofit2.Response
import retrofit2.http.GET

interface GetPopularMangasRepository {
    @GET("${API_CONFIG.API_MANGA}popular")
    suspend fun getPopularMangas(): Response<List<MangaAdapter>>
}