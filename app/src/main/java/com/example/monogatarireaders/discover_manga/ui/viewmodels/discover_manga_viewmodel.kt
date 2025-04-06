package com.example.monogatarireaders.discover_manga.ui.viewmodels

import com.example.monogatarireaders.discover_manga.data.repositories.MangaRepository
import com.example.monogatarireaders.discover_manga.domain.adapters.MangaAdapter
import androidx.lifecycle.ViewModel


class DiscoverMangaViewModel : ViewModel() {

    private val repository = MangaRepository()

    val trendingManga: List<MangaAdapter> = repository.getTrendingManga()
    val allManga: List<MangaAdapter> = repository.getAllManga()

    fun getMangaDetails(mangaId: String): MangaAdapter? {
        return allManga.find { it.id == mangaId }
    }


    fun onMangaClicked(manga: MangaAdapter) {

    }

    fun onStartReadingClicked() {

    }

    fun onContactUsClicked() {

    }
}