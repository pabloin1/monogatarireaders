package com.monogatari.app.core.data.states.viewmodels

import androidx.compose.runtime.compositionLocalOf
import com.monogatari.app.core.data.providers.viewmodel.ViewModelManager

val LocalViewModelProvider = compositionLocalOf<ViewModelManager> {
    error("No ViewModelProvider found in CompositionLocal")
}