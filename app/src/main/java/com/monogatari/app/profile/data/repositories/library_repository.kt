package com.monogatari.app.profile.data.repositories

import com.monogatari.app.core.domain.constants.API_CONFIG
import com.monogatari.app.shared.domain.adapters.MangaFavoriteAdapter
import retrofit2.Response
import retrofit2.http.GET

interface LibraryRepository {
    @GET("${API_CONFIG.API_INTERACTION}favorites")
    suspend fun getLibraryItems() : Response<List<MangaFavoriteAdapter>>
}