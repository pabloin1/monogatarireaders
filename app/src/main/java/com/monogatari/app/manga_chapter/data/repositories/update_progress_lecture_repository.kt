package com.monogatari.app.manga_chapter.data.repositories

import com.monogatari.app.core.domain.constants.API_CONFIG
import com.monogatari.app.manga_detail.domain.dtos.UpdateProgressLectureDto
import com.monogatari.app.shared.domain.adapters.MangaFavoriteAdapter
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UpdateProgressLectureRepository {
    @POST("${API_CONFIG.API_INTERACTION}read-progress")
    suspend fun updateProgressLecture(
        @Body() updateProgressLectureDto: UpdateProgressLectureDto
    ): Response<MangaFavoriteAdapter>
}