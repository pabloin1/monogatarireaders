package com.monogatari.app.manga_detail.ui.composables.chapter_section.chapter_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monogatari.app.core.ui.theme.BackgroundGrayItem
import com.monogatari.app.core.ui.theme.BorderGray
import com.monogatari.app.core.ui.theme.PrimaryWhite
import com.monogatari.app.manga_detail.domain.adapters.ChapterAdapter
import com.monogatari.app.shared.ui.composables.GlowingButton

@Composable
fun ChapterItem(
    coverImageResId : Int,
    chapter: ChapterAdapter,
    onChapterClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 80.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = BorderGray,
                shape = RoundedCornerShape(12.dp)
            )
            .background(BackgroundGrayItem)
            .padding(horizontal = 15.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.size(50.dp)
            ) {
                Image(
                    painter = painterResource(id = coverImageResId),
                    contentDescription = chapter.title,
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = chapter.title,
                    color = PrimaryWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = chapter.date,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.Red,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${chapter.views / 1000}.${(chapter.views % 1000) / 100}k",
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            GlowingButton(
                text = "Read",
                onClick = onChapterClick,
                modifier = Modifier
                    .width(80.dp)
                    .height(40.dp),
                fontSize = 12.sp,
                borderRadius = 8.dp,
            )
        }
    }
}