package com.example.monogatarireaders.discover_manga.ui.viewmodels

import com.example.monogatarireaders.discover_manga.data.repositories.MangaRepository
import com.example.monogatarireaders.discover_manga.domain.adapters.MangaAdapter
import androidx.lifecycle.ViewModel


class DiscoverMangaViewModel : ViewModel() {

    private val _discoverMangaRepository = MangaRepository()

    val trendingManga: List<MangaAdapter> = _discoverMangaRepository.getTrendingManga()

    fun onStartReadingClicked() {

    }

    fun onContactUsClicked() {

    }
}