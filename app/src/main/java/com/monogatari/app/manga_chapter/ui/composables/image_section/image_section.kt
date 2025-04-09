package com.monogatari.app.manga_chapter.ui.composables.image_section

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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.monogatari.app.core.data.local.network.NetworkManager
import com.monogatari.app.core.data.states.viewmodels.LocalViewModelProvider
import com.monogatari.app.core.ui.theme.BackgroundBlue
import java.io.File

@Composable
fun ImageSection(
    image : String,
    currentImageIndex : Int,
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    val isOnline = NetworkManager.isOnline()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(BackgroundBlue, shape = MaterialTheme.shapes.large)
    ) {
        AsyncImage(
            model = if(isOnline) image else File(image),
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