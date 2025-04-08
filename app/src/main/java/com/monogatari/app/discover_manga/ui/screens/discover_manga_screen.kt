package com.monogatari.app.discover_manga.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monogatari.app.core.data.states.viewmodels.LocalViewModelProvider
import com.monogatari.app.discover_manga.ui.composables.HeaderBannerDiscover
import com.monogatari.app.discover_manga.ui.composables.TrendingSection
import com.monogatari.app.shared.ui.layouts.AppLayout

@Composable
fun DiscoverMangaScreen() {
    val viewModel = LocalViewModelProvider.current.discoverMangaViewModel
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