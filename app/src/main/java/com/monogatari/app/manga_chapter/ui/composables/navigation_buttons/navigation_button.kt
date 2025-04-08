package com.monogatari.app.manga_chapter.ui.composables.navigation_buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.monogatari.app.core.ui.theme.BackgroundBlue
import com.monogatari.app.core.ui.theme.PrimaryWhite

@Composable
fun NavigationButton(
    onClick : () -> Unit,
    contentDescription : String = "",
    icon : ImageVector,
    isEnabled : Boolean = true
) {
    IconButton(
        onClick = onClick,
        enabled = isEnabled,
        modifier = Modifier
            .background(BackgroundBlue, shape = RoundedCornerShape(50.dp))
            .size(48.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (isEnabled) PrimaryWhite else Color.Gray,
            modifier = Modifier.size(35.dp)
        )
    }
}