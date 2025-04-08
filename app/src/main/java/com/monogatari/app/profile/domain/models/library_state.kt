package com.monogatari.app.profile.domain.models

import com.monogatari.app.profile.domain.adapters.LibraryAdapter

sealed class LibraryState {
    data object Idle : LibraryState()
    data object Loading : LibraryState()
    data class Success(val library: LibraryAdapter) : LibraryState()
    data class Error(val message: String) : LibraryState()
}