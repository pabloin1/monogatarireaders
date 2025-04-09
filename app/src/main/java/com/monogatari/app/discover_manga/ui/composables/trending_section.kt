package com.monogatari.app.discover_manga.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monogatari.app.core.ui.theme.GlowingYellow
import com.monogatari.app.core.ui.theme.PrimaryWhite
import com.monogatari.app.discover_manga.domain.models.DiscoverState
import com.monogatari.app.discover_manga.ui.viewmodels.DiscoverMangaViewModel
import com.monogatari.app.shared.ui.composables.CustomSnackBar
import com.monogatari.app.shared.ui.composables.OfflineMode

@Composable
fun TrendingSection( viewModel: DiscoverMangaViewModel ) {
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "TRENDING NOW",
            color = PrimaryWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when(state){
            is DiscoverState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = GlowingYellow
                )
            }
            is DiscoverState.Success -> {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.mangaList) { manga ->
                        TrendingMangaCard(
                            manga = manga,
                        )
                    }
                }
            }
            is DiscoverState.Error -> {
                CustomSnackBar(text = state.message)
            }
            is DiscoverState.Offline -> {
                OfflineMode()
            }
            else -> {}
        }
    }
}
