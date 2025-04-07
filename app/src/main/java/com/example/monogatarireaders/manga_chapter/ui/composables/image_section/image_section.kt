package com.example.monogatarireaders.manga_chapter.ui.composables.image_section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.monogatarireaders.core.ui.theme.BackgroundBlue

@Composable
fun ImageSection(
    image : Int,
    currentImageIndex : Int,
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(BackgroundBlue, shape = MaterialTheme.shapes.large)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Image ${currentImageIndex + 1}",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
                .pointerInput(currentImageIndex) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale =  (scale * zoom).coerceIn(1f, 3f)
                        if (scale > 1f) {
                            val newOffsetX = offsetX + pan.x
                            val newOffsetY = offsetY + pan.y

                            val maxX = (scale - 1f) * size.width / 2f
                            val maxY = (scale - 1f) * size.height / 2f

                            offsetX = newOffsetX.coerceIn(-maxX, maxX)
                            offsetY = newOffsetY.coerceIn(-maxY, maxY)
                        } else {
                            offsetX = 0f
                            offsetY = 0f
                        }
                    }
                }
        )
    }
}