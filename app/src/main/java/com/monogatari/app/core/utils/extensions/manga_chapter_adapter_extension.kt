package com.monogatari.app.core.utils.extensions

import com.monogatari.app.core.data.local.room.entities.ChapterEntity
import com.monogatari.app.core.data.local.room.entities.ChapterPageEntity
import com.monogatari.app.manga_chapter.domain.adapters.MangaChapterAdapter

fun MangaChapterAdapter.toEntity() = ChapterEntity(
    id = id,
    mangaId = mangaId,
    mangaTitle = mangaTitle,
    title = title,
    chapterNumber = chapterNumber,
    description = description,
    publishDate = publishDate,
    viewCount = viewCount,
    isRead = false,
    isDownloaded = false
)

fun MangaChapterAdapter.toPageEntities(): List<ChapterPageEntity> =
    pages.mapIndexed { index, pageUrl ->
        ChapterPageEntity(
            chapterId = id,
            pageUrl = pageUrl,
            pageIndex = index,
            isDownloaded = false
        )
    }