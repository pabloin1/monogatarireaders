package com.example.monogatarireaders.router.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.monogatarireaders.core.ui.theme.PrimaryBlack
import com.example.monogatarireaders.core.ui.theme.PrimaryWhite
import com.example.monogatarireaders.router.data.NavigationData

@Composable
fun BottomNavigationBar(routeSelected : MutableState<String>, modifier: Modifier = Modifier) {
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
                selected = routeSelected.value == item.label,
                onClick = {
                    routeSelected.value = item.label
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

