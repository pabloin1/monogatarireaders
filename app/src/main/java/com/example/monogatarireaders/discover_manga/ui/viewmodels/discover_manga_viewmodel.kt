package com.example.monogatarireaders.discover_manga.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.monogatarireaders.discover_manga.data.repositories.MangaRepository
import com.example.monogatarireaders.discover_manga.domain.adapters.MangaAdapter
import androidx.lifecycle.ViewModel


class DiscoverMangaViewModel(app : Application): AndroidViewModel(app) {

    private val _discoverMangaRepository = MangaRepository()

    val trendingManga: List<MangaAdapter> = _discoverMangaRepository.getTrendingManga()

    fun onStartReadingClicked() {

    }

    fun onContactUsClicked() {

    }
}