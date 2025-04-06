package com.example.monogatarireaders.profile.ui.composables.library_section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monogatarireaders.core.ui.theme.BackgroundGrayItem
import com.example.monogatarireaders.core.ui.theme.BorderGray
import com.example.monogatarireaders.core.ui.theme.GlowingYellow
import com.example.monogatarireaders.profile.domain.adapters.LibraryItemAdapter

@Composable
fun LibraryItem(
    libraryItem: LibraryItemAdapter
) {
    val progress = libraryItem.chaptersRead.toFloat() / libraryItem.totalChapters
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = BorderGray,
                shape = RoundedCornerShape(12.dp)
            )
            .background(BackgroundGrayItem)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = libraryItem.coverResourceId),
            contentDescription = "Manga Cover",
            modifier = Modifier
                .width(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(15.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = libraryItem.title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Last read: Ch. ${libraryItem.lastReadChapter} - ${libraryItem.lastReadDaysAgo} days ago",
                color = Color.Gray,
                fontSize = 12.sp
            )

            LinearProgressIndicator(
                progress = {
                    progress
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
                text =  "${libraryItem.chaptersRead}/${libraryItem.totalChapters}",
                color = Color.Gray,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
