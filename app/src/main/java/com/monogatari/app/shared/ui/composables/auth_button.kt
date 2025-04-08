package com.monogatari.app.shared.ui.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monogatari.app.core.ui.theme.BackgroundBlue
import com.monogatari.app.core.ui.theme.GlowingYellow

@Composable
fun AuthButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val buttonColor = if (enabled) {
        GlowingYellow
    } else {
        GlowingYellow.copy(alpha = 0.5f)
    }

    val textColor = BackgroundBlue
    val scale by animateFloatAsState(
        targetValue = if (enabled) 1f else 0.95f,
        animationSpec = tween(150)
    )

    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            disabledContainerColor = buttonColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .scale(scale)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            letterSpacing = 2.sp
        )
    }
}