package com.monogatari.app.manga_detail.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.monogatari.app.core.utils.extensions.toLocalDate
import com.monogatari.app.manga_detail.data.repositories.MangaDetailRepository
import com.monogatari.app.manga_detail.domain.models.ChapterSortOrder
import com.monogatari.app.manga_detail.domain.models.MangaDetailState
import kotlinx.coroutines.launch

class MangaDetailViewModel(app : Application): AndroidViewModel(app) {
    private val _state = mutableStateOf<MangaDetailState>(MangaDetailState.Loading)
    val state = _state
    private val _sortOrder = mutableStateOf(ChapterSortOrder.NEWEST)
    val sortOrder = _sortOrder


    private val _mangaDetailRepository = MangaDetailRepository()

    fun loadMangaDetail(mangaId: Int) {
        viewModelScope.launch {
            _state.value = MangaDetailState.Loading
            try {
                val detail = _mangaDetailRepository.getMangaDetailsById(mangaId)
                _state.value = MangaDetailState.Success(
                    detail
                )
            } catch (e: Exception) {
                _state.value = MangaDetailState.Error("Error loading manga details: ${e.message}")
            }
        }
    }

    fun updateSortOrder(order: ChapterSortOrder) {
        _sortOrder.value = order
        sortChapters()
    }

    private fun sortChapters() {
        val currentState = _state.value
        if (currentState is MangaDetailState.Success) {
            val sortedChapters = when (_sortOrder.value) {
                ChapterSortOrder.NEWEST -> currentState.manga.chapters.sortedByDescending { it.date.toLocalDate() }
                ChapterSortOrder.OLDEST -> currentState.manga.chapters.sortedBy { it.date.toLocalDate() }
            }

            _state.value = currentState.copy(
                manga = currentState.manga.copy(
                    chapters = sortedChapters
                )
            )
        }
    }

    fun addToLibrary() {

    }

    fun startReading() {

    }

    fun toggleFavorite() {

    }

    fun shareManga() {

    }
}