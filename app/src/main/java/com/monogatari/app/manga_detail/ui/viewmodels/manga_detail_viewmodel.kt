package com.monogatari.app.manga_detail.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.monogatari.app.core.data.local.network.NetworkManager
import com.monogatari.app.core.utils.extensions.toFormattedDate
import com.monogatari.app.manga_detail.data.services.AddMangaFavoriteService
import com.monogatari.app.manga_detail.data.services.DeleteMangaFavoriteService
import com.monogatari.app.manga_detail.domain.dtos.AddMangaFavoriteDto
import com.monogatari.app.manga_detail.domain.models.ChapterSortOrder
import com.monogatari.app.manga_detail.domain.models.MangaDetailState
import com.monogatari.app.manga_detail.domain.use_cases.GetMangaDetailLocalUseCase
import com.monogatari.app.manga_detail.domain.use_cases.GetMangaDetailUseCase
import kotlinx.coroutines.launch

class MangaDetailViewModel(app : Application): AndroidViewModel(app) {
    private val _state = mutableStateOf<MangaDetailState>(MangaDetailState.Loading)
    val state = _state
    private val _sortOrder = mutableStateOf(ChapterSortOrder.NEWEST)
    val sortOrder = _sortOrder

    private val _getMangaDetailUseCase = GetMangaDetailUseCase()
    private val _getMangaDetailLocalUseCase = GetMangaDetailLocalUseCase(app)
    private val _addMangaFavoriteService = AddMangaFavoriteService()
    private val _deleteMangaFavoriteService = DeleteMangaFavoriteService()

    fun loadMangaDetail(mangaId: Int) {
        viewModelScope.launch {
            if(NetworkManager.isOnline()){
                getInfoNetwork(mangaId)
            }else{
                getInfoLocal(mangaId)
            }
        }
    }

    suspend fun getInfoNetwork(mangaId: Int) {
        try {
            _getMangaDetailUseCase.execute(mangaId).fold(
                onSuccess = { manga ->
                    _state.value = MangaDetailState.Success(manga)
                    sortChapters() },
                onFailure = { error ->
                    _state.value = MangaDetailState.Error("Error loading manga details: ${error.message}")
                }
            )
        } catch (e: Exception) {
            _state.value = MangaDetailState.Error("Error loading manga details: ${e.message}")
        }
    }

    private fun getInfoLocal(mangaId: Int){
        viewModelScope.launch {
            try {
                _state.value = MangaDetailState.Loading
                val result = _getMangaDetailLocalUseCase.execute(mangaId)
                _state.value = MangaDetailState.Success(result)
                Log.d("MD_VM_TAG", "getInfoLocal: $result")
                sortChapters()
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
                ChapterSortOrder.NEWEST -> currentState.manga.chapters.sortedByDescending { it.publishDate.toFormattedDate() }
                ChapterSortOrder.OLDEST -> currentState.manga.chapters.sortedBy { it.publishDate.toFormattedDate() }
            }

            _state.value = currentState.copy(
                manga = currentState.manga.copy(
                    chapters = sortedChapters
                )
            )
        }
    }

    fun toggleFavorite() {
        val currentState = _state.value
        if (currentState is MangaDetailState.Success) {
            val manga = currentState.manga
            val favoriteDto = AddMangaFavoriteDto(manga.details.id.toInt())
            viewModelScope.launch {
                try {
                    _addMangaFavoriteService.addFavorite(favoriteDto)
                        .fold(
                            onSuccess = {
                                _state.value = MangaDetailState.Success(manga.copy(
                                    chapters = manga.chapters,
                                    details = manga.details.copy(inUserFavorites = !manga.details.inUserFavorites)
                                ))
                            },
                            onFailure = { error ->
                                _state.value = MangaDetailState.Error("Error toggling favorite: ${error.message}")
                            }
                        )
                } catch (e: Exception) {
                    _state.value = MangaDetailState.Error("Error toggling favorite: ${e.message}")
                }
            }
        }
    }

    fun deleteFavorite() {
        val currentState = _state.value
        if (currentState is MangaDetailState.Success) {
            val manga = currentState.manga
            viewModelScope.launch {
                try {
                    _deleteMangaFavoriteService.deleteFavorite(manga.details.id.toInt())
                        .fold(
                            onSuccess = {
                                _state.value = MangaDetailState.Success(manga.copy(
                                    chapters = manga.chapters,
                                    details = manga.details.copy(inUserFavorites = !manga.details.inUserFavorites
                                    )
                            ))
                                        },
                            onFailure = { error ->
                                _state.value = MangaDetailState.Error("Error deleting favorite: ${error.message}")
                            }
                    )
                } catch (e: Exception) {
                    _state.value = MangaDetailState.Error("Error deleting favorite: ${e.message}")
                }
            }
        }
    }

    fun shareManga() {

    }
}