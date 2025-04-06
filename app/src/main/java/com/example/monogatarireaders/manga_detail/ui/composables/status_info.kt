package com.example.monogatarireaders.manga_detail.ui.composables

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monogatarireaders.manga_detail.domain.adapters.MangaDetailAdapter

@Composable
fun StatusInfo(mangaDetail : MangaDetailAdapter) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Status: ",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Text(
                text = mangaDetail.status,
                color = Color.White,
                fontSize = 14.sp
            )
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(8.dp)
                    .background(
                        if (mangaDetail.status == "Completed") Color.Green else Color.Yellow,
                        CircleShape
                    )
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = mangaDetail.rating.toString(),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "Rating",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "${mangaDetail.chapters.size}",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "Chapters",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}