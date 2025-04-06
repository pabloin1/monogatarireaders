package com.example.monogatarireaders.manga_detail.ui.composables.top_manga_detail.top_manga_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monogatarireaders.core.ui.theme.BackgroundBlue
import com.example.monogatarireaders.core.ui.theme.PrimaryWhite

@Composable
fun GenreTag(genre : String) {
    Box(
        modifier = Modifier
            .background(
                BackgroundBlue,
                RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = genre,
            color = PrimaryWhite,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}