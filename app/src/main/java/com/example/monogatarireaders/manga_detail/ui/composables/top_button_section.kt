package com.example.monogatarireaders.manga_detail.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.monogatarireaders.router.data.NavigationData
import com.example.monogatarireaders.router.data.states.LocalRouter
import com.example.monogatarireaders.router.data.states.navigateTo

@Composable
fun TopButtonSection(
    firstPosition: Modifier = Modifier,
    secondPosition : Modifier = Modifier,
    onFavoriteClick: () -> Unit,
    onShareClick: () -> Unit,
) {
    val router = LocalRouter.current
    IconButton(
        onClick = { router.navigateTo(NavigationData.discover.label) },
        modifier = firstPosition
            .padding(16.dp)
            .size(48.dp)
            .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White
        )
    }

    // Favorite and share buttons
    Row(
        modifier = secondPosition
            .padding(16.dp)
    ) {
        IconButton(
            onClick = onFavoriteClick,
            modifier = Modifier
                .size(40.dp)
                .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        IconButton(
            onClick = onShareClick,
            modifier = Modifier
                .size(40.dp)
                .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share",
                tint = Color.White
            )
        }
    }
}