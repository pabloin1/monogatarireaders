package com.monogatari.app.discover_manga.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.monogatari.app.core.data.local.network.NetworkManager
import com.monogatari.app.discover_manga.data.services.GetPopularMangaService
import com.monogatari.app.discover_manga.domain.models.DiscoverState
import kotlinx.coroutines.launch

class DiscoverMangaViewModel(app : Application): AndroidViewModel(app) {
    val state = mutableStateOf<DiscoverState>(DiscoverState.Idle)

    private val _getPopularMangaService = GetPopularMangaService()

    init {
        viewModelScope.launch {
            NetworkManager.observeNetworkState().collect { isOnline ->
                if (isOnline) {
                    getTrendingManga()
                } else {
                    state.value = DiscoverState.Offline
                }
            }
        }
    }

    private fun getTrendingManga() {
        viewModelScope.launch {
            try {
                state.value = DiscoverState.Loading
               _getPopularMangaService.getPopularMangas().fold(
                    onSuccess = { mangas ->
                        state.value = DiscoverState.Success(mangas)
                    },
                    onFailure = { error ->
                        state.value = DiscoverState.Error(error.message.toString())
                    }
               )
            }catch (e: Exception) {
                state.value = DiscoverState.Error(e.message.toString())
            }
        }
    }

    fun onStartReadingClicked() {

    }

    fun onContactUsClicked() {

    }
}