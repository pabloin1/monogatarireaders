package com.monogatari.app.manga_detail.domain.models

import com.monogatari.app.manga_detail.domain.adapters.MangaDetailAdapter

sealed class MangaDetailState {
    data object Idle : MangaDetailState()
    data object Loading : MangaDetailState()
    data class Success(val manga: MangaDetailAdapter) : MangaDetailState()
    data class Error(val message: String) : MangaDetailState()
}