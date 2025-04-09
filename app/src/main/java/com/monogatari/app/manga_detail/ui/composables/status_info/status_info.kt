package com.monogatari.app.manga_detail.ui.composables.status_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monogatari.app.manga_detail.domain.adapters.MangaDetailAdapter

@Composable
fun StatusInfo(mangaDetail : MangaDetailAdapter) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Status: ",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Text(
                text = if (mangaDetail.completed) "Completed" else "Ongoing",
                color = Color.White,
                fontSize = 14.sp
            )
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(8.dp)
                    .background(
                        if (mangaDetail.completed) Color.Green else Color.Yellow,
                        CircleShape
                    )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            StatusItem(titleValue = "Rating", value = mangaDetail.averageRating.toString())
            Spacer(modifier = Modifier.width(16.dp))
            StatusItem(titleValue = "Chapters", value = mangaDetail.chapterCount.toString())
        }
    }
}