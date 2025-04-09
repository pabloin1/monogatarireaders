package com.monogatari.app.manga_detail.ui.composables

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
import com.monogatari.app.manga_detail.domain.adapters.MangaDetailChapterAdapter
import com.monogatari.app.manga_detail.domain.models.ChapterSortOrder
import com.monogatari.app.manga_detail.ui.composables.chapter_section.ChaptersSection
import com.monogatari.app.manga_detail.ui.composables.status_info.StatusInfo
import com.monogatari.app.manga_detail.ui.composables.synopsis_section.SynopsisSection
import com.monogatari.app.manga_detail.ui.composables.top_manga_detail.TopContentMangaDetail
import com.monogatari.app.shared.ui.composables.CustomOutlinedButton
import com.monogatari.app.shared.ui.composables.GlowingButton

@Composable
fun MangaDetailContent(
    mangaDetail: MangaDetailChapterAdapter,
    onStartReading: () -> Unit,
    onAddToLibrary: () -> Unit,
    onFavoriteClick: () -> Unit,
    onShareClick: () -> Unit,
    sorteBy : ChapterSortOrder = ChapterSortOrder.NEWEST,
    onSortOrder :(ChapterSortOrder) -> Unit,
    libraryString : String = "ADD TO LIBRARY",
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
       TopContentMangaDetail(
           mangaDetail = mangaDetail.details,
           onFavoriteClick = onFavoriteClick,
           onShareClick = onShareClick,
       )
        StatusInfo(mangaDetail = mangaDetail.details)

        SynopsisSection(synopsis = mangaDetail.details.description)

        // Action buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlowingButton(text =  "START READING", onClick = onStartReading, borderRadius = 10.dp)
            CustomOutlinedButton(onClick = onAddToLibrary, text = libraryString , borderRadius = 10.dp, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(14.dp))

        ChaptersSection(
            chapters = mangaDetail.chapters,
            mangaId = mangaDetail.details.id.toInt(),
            onSortOrder = onSortOrder,
            sorteBy = sorteBy,
            coverImgUrl = mangaDetail.details.coverImageUrl,
        )
    }
}
