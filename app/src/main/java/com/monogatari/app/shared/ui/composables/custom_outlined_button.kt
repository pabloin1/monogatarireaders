package com.monogatari.app.shared.ui.composables

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomOutlinedButton(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    text: String,
    borderRadius : Dp = 24.dp,
    fontSize : TextUnit = 17.sp,
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier
            .defaultMinSize( minHeight = 48.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            brush = Brush.horizontalGradient(
                listOf(Color.White, Color.White)
            )
        ),
        shape = RoundedCornerShape(borderRadius)
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = text,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            fontSize = fontSize,
        )
    }
}