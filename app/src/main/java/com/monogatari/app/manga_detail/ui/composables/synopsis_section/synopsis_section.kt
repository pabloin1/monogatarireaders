package com.monogatari.app.manga_detail.ui.composables.synopsis_section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monogatari.app.core.ui.theme.PrimaryWhite

@Composable
fun SynopsisSection(synopsis: String) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Synopsis",
            color = PrimaryWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            textAlign = TextAlign.Justify,
            text = synopsis,
            color = Color.LightGray,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}