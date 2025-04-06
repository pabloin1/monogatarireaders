package com.example.monogatarireaders.manga_detail.ui.composables.chapter_section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monogatarireaders.manga_detail.domain.adapters.MangaDetailAdapter
import com.example.monogatarireaders.manga_detail.domain.models.ChapterSortOrder
import com.example.monogatarireaders.manga_detail.ui.composables.chapter_section.chapter_item.ChapterItem
import com.example.monogatarireaders.manga_detail.ui.composables.chapter_section.sort_dropdown.SortDropdown

@Composable
fun ChaptersSection(
    mangaDetail : MangaDetailAdapter,
    onChapterSelected: (Int) -> Unit,
    sorteBy : ChapterSortOrder = ChapterSortOrder.NEWEST,
    onSortOrder :(ChapterSortOrder) -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Chapters",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "Sort by: ", color = Color.Gray, fontSize = 14.sp)
                SortDropdown(selectedOption = sorteBy.value, onOptionSelected = onSortOrder)
            }
        }

        // Chapter list
        mangaDetail.chapters.forEach { chapter ->
            ChapterItem(
                chapter = chapter,
                onChapterClick = { onChapterSelected(chapter.id) },
                coverImageResId = mangaDetail.coverImageResId,
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}