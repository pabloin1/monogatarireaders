package com.example.monogatarireaders.discover_manga.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.monogatarireaders.discover_manga.ui.composables.HeaderBannerDiscover
import com.example.monogatarireaders.discover_manga.ui.composables.TrendingSection
import com.example.monogatarireaders.discover_manga.ui.viewmodels.DiscoverMangaViewModel
import com.example.monogatarireaders.shared.ui.layouts.AppLayout

@Composable
fun DiscoverMangaScreen(
    viewModel: DiscoverMangaViewModel = remember { DiscoverMangaViewModel() }
) {
    AppLayout(
        children = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                HeaderBannerDiscover(
                    onStartReadingClick = { viewModel.onStartReadingClicked() },
                    onContactUsClick = { viewModel.onContactUsClicked() }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TrendingSection(
                    trendingManga = viewModel.trendingManga,
                )
            }
    })
}