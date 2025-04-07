package com.example.monogatarireaders.core.data.states.viewmodels

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.example.monogatarireaders.core.data.providers.viewmodel.ViewModelManager

@Composable
fun ViewModelManagerProvider(
    context : Context,
    content: @Composable () -> Unit
) {
    val viewModelManager = remember{ ViewModelManager(context = context) }

    CompositionLocalProvider(LocalViewModelProvider provides viewModelManager) {
        content()
    }
}