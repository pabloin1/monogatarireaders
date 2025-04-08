package com.monogatari.app.discover_manga.domain.models

import com.monogatari.app.shared.domain.adapters.MangaAdapter

sealed class DiscoverState {
    data object Idle : DiscoverState()
    data object Loading : DiscoverState()
    data class Success(val mangaList: List<MangaAdapter>) : DiscoverState()
    data class Error(val message: String) : DiscoverState()
}