package com.example.monogatarireaders.manga_detail.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.monogatarireaders.manga_detail.domain.adapters.MangaDetailAdapter

@Composable
fun TopContentMangaDetail(
    mangaDetail: MangaDetailAdapter,
    onFavoriteClick: () -> Unit,
    onShareClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        BannerManga(mangaDetail = mangaDetail)
        TopMangaInfo(mangaDetail = mangaDetail, modifier = Modifier.align(Alignment.BottomStart))
        TopButtonSection(
            firstPosition = Modifier.align(Alignment.TopStart),
            secondPosition = Modifier.align(Alignment.TopEnd),
            onFavoriteClick = {},
            onShareClick = {},
        )
    }

}