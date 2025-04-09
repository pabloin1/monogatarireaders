package com.monogatari.app.profile.ui.composables.library_section

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.monogatari.app.core.ui.theme.BackgroundGrayItem
import com.monogatari.app.core.ui.theme.BorderGray
import com.monogatari.app.core.ui.theme.GlowingYellow
import com.monogatari.app.router.data.NavigationData
import com.monogatari.app.router.data.states.LocalRouter
import com.monogatari.app.router.data.states.navigateTo
import com.monogatari.app.shared.domain.adapters.MangaFavoriteAdapter

@Composable
fun LibraryItem(
    libraryItem: MangaFavoriteAdapter
) {
    val route = LocalRouter.current
    val lastRead = libraryItem.lastReadChapter?.toFloat() ?: 0f
    val totalChapters = libraryItem.manga.chapterCount?.toFloat() ?: 1f
    val progress = if (totalChapters > 0) lastRead / totalChapters else 0f
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 110.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = BorderGray,
                shape = RoundedCornerShape(12.dp)
            )
            .background(BackgroundGrayItem)
            .padding(15.dp)
            .clickable { route.navigateTo(NavigationData.detailRoute(libraryItem.manga.id.toString())) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = libraryItem.manga.coverImageUrl,
            contentDescription = "Manga Cover",
            modifier = Modifier
                .width(60.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(15.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = libraryItem.manga.title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Last read: Ch. ${libraryItem.lastReadChapter}",
                color = Color.Gray,
                fontSize = 12.sp
            )

            LinearProgressIndicator(
                progress = {
                    if (progress.isNaN() || progress.isInfinite()) 0f else progress
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(10.dp)
                    .padding(top = 4.dp)
                    .clip(RoundedCornerShape(20.dp)),
                color = GlowingYellow,
                trackColor = Color(0xFF333333),
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Surface(
                modifier = Modifier
                    .width(70.dp)
                    .height(30.dp)
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(20.dp)),
                color = Color(0xFF003b19)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Updated",
                    color = Color(0XFF31a26c),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text =  "${lastRead}/${totalChapters}",
                color = Color.Gray,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
