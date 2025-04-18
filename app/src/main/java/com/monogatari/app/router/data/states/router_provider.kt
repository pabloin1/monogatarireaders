package com.monogatari.app.router.data.states

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.monogatari.app.router.data.NavigationData

@Composable
fun RouterProvider(
    initialRoute: String = NavigationData.discover.label,
    content: @Composable () -> Unit
) {
    val routerState = rememberSaveable{ mutableStateOf(initialRoute) }

    CompositionLocalProvider(LocalRouter provides routerState) {
        content()
    }
}