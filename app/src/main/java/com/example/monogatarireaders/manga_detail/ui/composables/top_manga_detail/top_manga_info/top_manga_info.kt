package com.example.monogatarireaders.manga_detail.ui.composables.top_manga_detail.top_manga_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monogatarireaders.core.ui.theme.BackgroundBlue
import com.example.monogatarireaders.core.ui.theme.SecundaryGlowingYellow
import com.example.monogatarireaders.manga_detail.domain.adapters.MangaDetailAdapter

@Composable
fun TopMangaInfo(mangaDetail : MangaDetailAdapter, modifier: Modifier = Modifier) {
    // Manga cover and title info
    Row(
        modifier = modifier
            .padding(16.dp)
    ) {
        // Cover image
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.size(100.dp, 150.dp)
        ) {
            Image(
                painter = painterResource(id = mangaDetail.coverImageResId),
                contentDescription = mangaDetail.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.border(
                    width = 2.dp,
                    color = BackgroundBlue,
                    shape = RoundedCornerShape(8.dp)
                )
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Title and author
        Column(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text(
                    text = mangaDetail.title,
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    style = TextStyle(
                        shadow = Shadow(
                            color = SecundaryGlowingYellow,
                            offset = Offset(0f, 0f),
                            blurRadius = 6f
                        )
                    )
                )
                RatingSquares(rating = mangaDetail.rating)
            }


            Text(
                text = "By ${mangaDetail.author}",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Genre tags
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                mangaDetail.genres.take(3).forEach { genre ->
                    GenreTag(genre = genre)
                }
            }
        }
    }
}