package com.example.monogatarireaders.core.data.states.viewmodels

import androidx.compose.runtime.compositionLocalOf
import com.example.monogatarireaders.core.data.providers.viewmodel.ViewModelManager

val LocalViewModelProvider = compositionLocalOf<ViewModelManager> {
    error("No ViewModelProvider found in CompositionLocal")
}