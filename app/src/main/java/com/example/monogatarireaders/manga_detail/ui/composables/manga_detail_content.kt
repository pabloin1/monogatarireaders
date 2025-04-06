package com.example.monogatarireaders.manga_detail.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monogatarireaders.manga_detail.domain.adapters.MangaDetailAdapter
import com.example.monogatarireaders.manga_detail.domain.models.ChapterSortOrder
import com.example.monogatarireaders.manga_detail.ui.composables.chapter_section.ChaptersSection
import com.example.monogatarireaders.manga_detail.ui.composables.status_info.StatusInfo
import com.example.monogatarireaders.manga_detail.ui.composables.synopsis_section.SynopsisSection
import com.example.monogatarireaders.manga_detail.ui.composables.top_manga_detail.TopContentMangaDetail
import com.example.monogatarireaders.shared.ui.composables.CustomOutlinedButton
import com.example.monogatarireaders.shared.ui.composables.GlowingButton

@Composable
fun MangaDetailContent(
    mangaDetail: MangaDetailAdapter,
    onStartReading: () -> Unit,
    onAddToLibrary: () -> Unit,
    onFavoriteClick: () -> Unit,
    onShareClick: () -> Unit,
    onChapterSelected: (Int) -> Unit,
    sorteBy : ChapterSortOrder = ChapterSortOrder.NEWEST,
    onSortOrder :(ChapterSortOrder) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
       TopContentMangaDetail(
           mangaDetail = mangaDetail,
           onFavoriteClick = onFavoriteClick,
           onShareClick = onShareClick,
       )
        StatusInfo(mangaDetail = mangaDetail)

        SynopsisSection(synopsis = mangaDetail.synopsis)

        // Action buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlowingButton(text =  "START READING", onClick = onStartReading, borderRadius = 10.dp)
            CustomOutlinedButton(onClick = onAddToLibrary, text = "ADD TO LIBRARY" , borderRadius = 10.dp, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(14.dp))

        ChaptersSection(
            mangaDetail = mangaDetail,
            onChapterSelected = onChapterSelected,
            onSortOrder = onSortOrder,
            sorteBy = sorteBy
        )
    }
}
