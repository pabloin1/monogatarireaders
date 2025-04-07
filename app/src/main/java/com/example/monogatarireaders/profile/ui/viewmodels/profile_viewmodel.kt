package com.example.monogatarireaders.profile.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.monogatarireaders.profile.data.repositories.CollectionRepository
import com.example.monogatarireaders.profile.data.repositories.LibraryRepository
import com.example.monogatarireaders.profile.domain.adapters.LibraryItemAdapter
import com.example.monogatarireaders.profile.domain.models.CollectibleItemModel

class ProfileViewModel(app : Application): AndroidViewModel(app) {
    private val _collectionRepository = CollectionRepository()
    private val _libraryRepository = LibraryRepository()

    val userCollections : List<CollectibleItemModel> = _collectionRepository.getCollections()
    val userLibrary : List<LibraryItemAdapter> = _libraryRepository.getLibraryItems()
}