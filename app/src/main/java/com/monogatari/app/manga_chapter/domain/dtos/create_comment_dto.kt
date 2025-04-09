package com.monogatari.app.manga_chapter.domain.dtos

data class CreateCommentDTO(
    val chapterId : Long,
    val content : String
)