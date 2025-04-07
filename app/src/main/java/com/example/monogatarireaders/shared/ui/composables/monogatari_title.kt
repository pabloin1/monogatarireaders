package com.example.monogatarireaders.shared.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monogatarireaders.core.ui.theme.GlowingYellow


@Composable
fun MonogatariTitle(text: String) {
    Text(
        text = text,
        color = GlowingYellow,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 2.dp)
    )
    Text(
        text = "STORIES",
        color = GlowingYellow,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 4.sp
    )
}
