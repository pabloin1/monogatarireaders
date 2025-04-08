package com.monogatari.app.discover_manga.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.monogatari.app.discover_manga.data.repositories.MangaRepository
import com.monogatari.app.discover_manga.domain.adapters.MangaAdapter


class DiscoverMangaViewModel(app : Application): AndroidViewModel(app) {

    private val _discoverMangaRepository = MangaRepository()

    val trendingManga: List<MangaAdapter> = _discoverMangaRepository.getTrendingManga()

    fun onStartReadingClicked() {

    }

    fun onContactUsClicked() {

    }
}