package com.monogatari.app.profile.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.monogatari.app.profile.data.repositories.CollectionRepository
import com.monogatari.app.profile.data.repositories.LibraryRepository
import com.monogatari.app.profile.domain.adapters.LibraryItemAdapter
import com.monogatari.app.profile.domain.models.CollectibleItemModel

class ProfileViewModel(app : Application): AndroidViewModel(app) {
    private val _collectionRepository = CollectionRepository()
    private val _libraryRepository = LibraryRepository()

    val userCollections : List<CollectibleItemModel> = _collectionRepository.getCollections()
    val userLibrary : List<LibraryItemAdapter> = _libraryRepository.getLibraryItems()
}