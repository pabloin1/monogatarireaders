package com.monogatari.app.manga_detail.domain.use_cases

import com.monogatari.app.manga_detail.data.services.GetMangaChaptersService
import com.monogatari.app.manga_detail.data.services.GetMangaDetailService
import com.monogatari.app.manga_detail.domain.adapters.MangaDetailChapterAdapter
import com.monogatari.app.profile.domain.adapters.LibraryAdapter

class GetMangaDetailUseCase() {
    private val _getMangaDetailService = GetMangaDetailService()
    private val _getMangaChaptersService = GetMangaChaptersService()

    suspend fun execute(mangaId: Int): Result<MangaDetailChapterAdapter> {
        return try {
            val mangaDetail = _getMangaDetailService.getMangaDetail(mangaId)
            mangaDetail.fold(
                onSuccess = { detail ->
                    val chapters = _getMangaChaptersService.getMangaChapters(mangaId)
                    chapters.fold(
                        onSuccess = { chapterList ->
                            Result.success(
                                MangaDetailChapterAdapter(
                                    chapters = chapterList,
                                    details = detail
                                )
                            )
                        },
                        onFailure = { error ->
                            Result.failure(Exception(error.message))
                        }
                    )
                },
                onFailure = { error ->
                    Result.failure(Exception(error.message))
                }
            )
        } catch (e: Exception) {
            Result.failure(Exception(e.message))
        }
    }
}