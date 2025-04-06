package com.example.monogatarireaders.shared.ui.composables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monogatarireaders.core.ui.theme.GlowingYellow

@Composable
fun GlowingButton(
    modifier: Modifier = Modifier ,
    text : String ,onClick: () -> Unit,
    blurInitialValue : Float = 8f,
    blurTargetValue : Float = 13f,
    fontSize : TextUnit = 17.sp,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "GlowingButton")
    val animatedGlow = infiniteTransition.animateFloat(
        initialValue = blurInitialValue,
        targetValue = blurTargetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "GlowingButton"
    )

    Box(
        modifier = modifier
            .defaultMinSize( minHeight = 38.dp)
            .drawBehind {
                drawRoundRect(
                    color = GlowingYellow,
                    cornerRadius = CornerRadius(24.dp.toPx()),
                    size = size,
                    alpha = 0.8f,
                    style = Fill,
                    blendMode = BlendMode.SrcOver,
                )
                drawRoundRect(
                    color = GlowingYellow,
                    topLeft = Offset(-animatedGlow.value, -animatedGlow.value),
                    size = Size(size.width + animatedGlow.value * 2, size.height + animatedGlow.value * 2),
                    cornerRadius = CornerRadius(24.dp.toPx() + animatedGlow.value),
                    alpha = 0.4f
                )
            }
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.defaultMinSize( minHeight = 38.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GlowingYellow
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = text,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = fontSize,
            )
        }
    }
}
