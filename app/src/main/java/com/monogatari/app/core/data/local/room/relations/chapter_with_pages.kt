package com.monogatari.app.core.data.local.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.monogatari.app.core.data.local.room.entities.ChapterEntity
import com.monogatari.app.core.data.local.room.entities.ChapterPageEntity
import com.monogatari.app.manga_chapter.domain.adapters.MangaChapterAdapter

class ChapterWithPages {
    @Embedded
    lateinit var chapter: ChapterEntity

    @Relation(
        parentColumn = "id",
        entityColumn = "chapterId"
    )
    lateinit var pages: List<ChapterPageEntity>

    fun toMangaChapterAdapter() = MangaChapterAdapter(
        id = chapter.id,
        mangaId = chapter.mangaId,
        mangaTitle = chapter.mangaTitle,
        title = chapter.title,
        chapterNumber = chapter.chapterNumber,
        description = chapter.description,
        publishDate = chapter.publishDate,
        pages = pages.sortedBy { it.pageIndex }.map { it.pageUrl },
        viewCount = chapter.viewCount
    )
}
