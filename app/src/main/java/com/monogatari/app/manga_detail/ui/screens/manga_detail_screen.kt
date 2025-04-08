package com.monogatari.app.manga_detail.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.monogatari.app.core.data.states.viewmodels.LocalViewModelProvider
import com.monogatari.app.manga_detail.domain.models.MangaDetailState
import com.monogatari.app.manga_detail.ui.composables.MangaDetailContent
import com.monogatari.app.router.data.NavigationData
import com.monogatari.app.router.data.states.LocalRouter
import com.monogatari.app.router.data.states.navigateTo

@Composable
fun MangaDetailScreen(
    mangaId: Int,
) {
    val viewModel = LocalViewModelProvider.current.mangaDetailViewModel

    LaunchedEffect(mangaId) {
        viewModel.loadMangaDetail(mangaId)
    }

    val state by viewModel.state
    val sortOrder by viewModel.sortOrder
    val router = LocalRouter.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        when(state){
            is MangaDetailState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF8BC34A)
                )
            }
            is MangaDetailState.Error -> {
                val error = (state as MangaDetailState.Error).message
                Text(
                    text = error,
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
            is MangaDetailState.Success -> {
                val mangaDetail = (state as MangaDetailState.Success).manga
                MangaDetailContent(
                    mangaDetail = mangaDetail,
                    onStartReading = {  },
                    onAddToLibrary = { viewModel.addToLibrary() },
                    onFavoriteClick = { viewModel.toggleFavorite() },
                    onShareClick = { viewModel.shareManga() },
                    onChapterSelected = {router.navigateTo(NavigationData.manga_chapter)},
                    sorteBy = sortOrder,
                    onSortOrder = { viewModel.updateSortOrder(it) }
                )
            }
            is MangaDetailState.Idle -> {}
        }
    }
}