package com.monogatari.app.router.domain.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem(
    val icon: ImageVector,
    val contentDescription: String,
    val label: String,
    val color: Color
)
