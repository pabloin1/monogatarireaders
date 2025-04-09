package com.monogatari.app.manga_detail.ui.composables.top_manga_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monogatari.app.manga_detail.domain.adapters.MangaDetailAdapter
import com.monogatari.app.manga_detail.domain.adapters.MangaDetailChapterAdapter
import com.monogatari.app.manga_detail.ui.composables.top_manga_detail.banner_manga.BannerManga
import com.monogatari.app.manga_detail.ui.composables.top_manga_detail.top_button_section.TopButtonSection
import com.monogatari.app.manga_detail.ui.composables.top_manga_detail.top_manga_info.TopMangaInfo

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
        BannerManga(coverImgUrl = mangaDetail.coverImageUrl)
        TopMangaInfo(mangaDetail = mangaDetail, modifier = Modifier.align(Alignment.BottomStart))
        TopButtonSection(
            firstPosition = Modifier.align(Alignment.TopStart),
            secondPosition = Modifier.align(Alignment.TopEnd),
            onFavoriteClick = onFavoriteClick,
            onShareClick = onShareClick,
            isFavorite = mangaDetail.inUserFavorites
        )
    }

}