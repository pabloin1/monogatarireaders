package com.monogatari.app.manga_detail.domain.adapters

data class MangaDetailChapterAdapter(
    val chapters : List<ChapterAdapter>,
    var details : MangaDetailAdapter,
)