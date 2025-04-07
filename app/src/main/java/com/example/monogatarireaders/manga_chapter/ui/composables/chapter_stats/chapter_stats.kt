package com.example.monogatarireaders.manga_chapter.ui.composables.chapter_stats

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.monogatarireaders.shared.ui.composables.GlowingButton

@Composable
fun ChapterStats(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "3.4k",
            color = Color.Red,
        )
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier
                .size(38.dp)
                .padding(start = 4.dp, end = 16.dp),
        )
        Text(
            text = "3.6k",
            modifier = Modifier.padding(start = 4.dp),
            color = Color.Gray,
        )
        Icon(
            imageVector = Icons.Outlined.Visibility,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .size(38.dp)
                .padding(start = 4.dp, end = 16.dp),
        )
        Spacer(modifier = Modifier.weight(1f))
        GlowingButton(text = "Donate", onClick = { }, borderRadius = 35.dp)
    }
}