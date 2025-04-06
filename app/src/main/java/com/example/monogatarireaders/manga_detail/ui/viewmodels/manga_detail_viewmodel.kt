package com.example.monogatarireaders.manga_detail.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monogatarireaders.core.utils.extensions.toLocalDate
import com.example.monogatarireaders.manga_detail.data.repositories.MangaDetailRepository
import com.example.monogatarireaders.manga_detail.domain.models.ChapterSortOrder
import com.example.monogatarireaders.manga_detail.domain.models.MangaDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MangaDetailViewModel : ViewModel() {
    private val _state = MutableStateFlow<MangaDetailState>(MangaDetailState.Loading)
    val state: StateFlow<MangaDetailState> = _state
    private val _sortOrder = MutableStateFlow(ChapterSortOrder.NEWEST)
    val sortOrder: StateFlow<ChapterSortOrder> = _sortOrder


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