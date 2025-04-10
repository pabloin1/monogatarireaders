package com.monogatari.app.manga_chapter.domain.use_cases

import android.util.Log
import com.monogatari.app.manga_chapter.data.services.GetMangaChapterByChapterIdService
import com.monogatari.app.manga_chapter.data.services.UpdateProgressLectureService
import com.monogatari.app.manga_chapter.domain.adapters.MangaChapterAdapter
import com.monogatari.app.manga_detail.domain.dtos.UpdateProgressLectureDto

class GetMangaChapterInfoUseCase {
    private val _getMangaChapterByChapterIdService = GetMangaChapterByChapterIdService()
    private val _updateProgressLectureService = UpdateProgressLectureService()

    suspend fun execute(chapterId: Int): Result<MangaChapterAdapter> {
        return try {
            val mangaChapter = _getMangaChapterByChapterIdService.getMangaChapter(chapterId)
            mangaChapter.fold(
                onSuccess = { detail ->
                    val updateProgressLectureDto = UpdateProgressLectureDto(
                        mangaId = detail.mangaId.toInt(),
                        lastReadChapter = detail.id.toInt(),
                    )
                    val chapters = _updateProgressLectureService.updateProgress(updateProgressLectureDto)
                    chapters.fold(
                        onSuccess = {
                            Result.success(
                                MangaChapterAdapter(
                                    id = detail.id,
                                    mangaId = detail.mangaId,
                                    mangaTitle = detail.mangaTitle,
                                    title = detail.title,
                                    chapterNumber = detail.chapterNumber,
                                    description = detail.description,
                                    publishDate = detail.publishDate,
                                    pages = detail.pages,
                                    viewCount = detail.viewCount,
                                )
                            )
                        },
                        onFailure = { error ->
                            Log.d("GET_MANGA_CHAPTER_ERROR", "execute: error updating progress: ${error.message}")
                            Result.success(
                                MangaChapterAdapter(
                                    id = detail.id,
                                    mangaId = detail.mangaId,
                                    mangaTitle = detail.mangaTitle,
                                    title = detail.title,
                                    chapterNumber = detail.chapterNumber,
                                    description = detail.description,
                                    publishDate = detail.publishDate,
                                    pages = detail.pages,
                                    viewCount = detail.viewCount,
                                )
                            )
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