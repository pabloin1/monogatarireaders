package com.monogatari.app.router.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.monogatari.app.core.ui.theme.PrimaryBlack
import com.monogatari.app.core.ui.theme.PrimaryWhite
import com.monogatari.app.router.data.NavigationData
import com.monogatari.app.router.data.states.LocalRouter
import com.monogatari.app.router.data.states.currentRoute
import com.monogatari.app.router.data.states.navigateTo

@Composable
fun BottomNavigationBar( modifier: Modifier = Modifier) {
    val currentRoute = LocalRouter.current
    val routes = NavigationData.routes

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        containerColor = PrimaryBlack
    ) {
        routes.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.contentDescription) },
                label = { Text(item.label) },
                selected = currentRoute.currentRoute() == item.label,
                onClick = {
                    currentRoute.navigateTo(item.label)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = item.color,
                    unselectedIconColor = PrimaryWhite,
                    selectedTextColor = item.color,
                    unselectedTextColor = PrimaryWhite,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

