package com.monogatari.app.manga_detail.ui.composables.top_manga_detail.top_manga_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.round

@Composable
fun RatingSquares(rating : Float) {
    val filledSquares = round(rating).toInt()

    repeat(5) { index ->
        Box(
            modifier = Modifier
                .size(15.dp)
                .background(
                    if (index < filledSquares) Color(0xFFFFD700) else Color.Gray.copy(alpha = 0.9f),
                    RoundedCornerShape(4.dp)
                )
        )
    }
}