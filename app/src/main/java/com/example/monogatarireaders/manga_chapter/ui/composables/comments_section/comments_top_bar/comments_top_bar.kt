package com.example.monogatarireaders.manga_chapter.ui.composables.comments_section.comments_top_bar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monogatarireaders.core.ui.theme.PrimaryWhite

@Composable
fun CommentsTopBar(
    commentsCount : Int,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Comments",
            color = PrimaryWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = commentsCount.toString(),
            color = Color.Gray,
            fontSize = 14.sp,
        )
        Spacer(modifier = Modifier.width(10.dp))
        ClickableText(
            text = AnnotatedString("See all"),
            onClick = {},
            style = TextStyle(
                color = PrimaryWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}