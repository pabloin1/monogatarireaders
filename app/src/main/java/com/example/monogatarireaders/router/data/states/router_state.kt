package com.example.monogatarireaders.router.data.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf

val LocalRouter = compositionLocalOf<MutableState<String>> {
    error("LocalRouter is not initialized. Wrap your child with RouterProvider.")
}

fun MutableState<String>.navigateTo(route: String) {
    this.value = route
}

fun MutableState<String>.currentRoute(): String = this.value