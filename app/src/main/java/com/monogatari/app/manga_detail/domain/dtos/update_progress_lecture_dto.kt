package com.monogatari.app.manga_detail.domain.dtos

data class UpdateProgressLectureDto(
    val mangaId: Int,
    val lastReadChapter : Int
)