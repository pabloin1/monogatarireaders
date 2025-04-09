package com.monogatari.app.manga_detail.domain.use_cases

import android.content.Context
import android.util.Log
import com.monogatari.app.core.data.local.room.database.MangaDatabase
import com.monogatari.app.manga_detail.domain.adapters.ChapterAdapter
import com.monogatari.app.manga_detail.domain.adapters.MangaDetailAdapter
import com.monogatari.app.manga_detail.domain.adapters.MangaDetailChapterAdapter
import com.monogatari.app.shared.domain.adapters.CreatorAdapter

class GetMangaDetailLocalUseCase(context: Context){
    private val mangaDetailDao = MangaDatabase.getDatabase(context).mangaDao()
    private val chapterDao = MangaDatabase.getDatabase(context).chapterDao()

    suspend fun execute(mangaId: Int) : MangaDetailChapterAdapter {
        val mangaDetail = mangaDetailDao.getMangaWithDetails(mangaId.toLong())
        val chapters = chapterDao.getChaptersByMangaId(mangaId.toLong())
        val all = chapterDao.getAllChapters()
        Log.d("GM_VM_TAG", "getInfoLocal: $all")
        val mangaDetailWithChapters = chapters.map {
            ChapterAdapter(
                chapterNumber = it.chapterNumber.toInt(),
                id = it.id.toInt(),
                mangaId = it.mangaId.toInt(),
                publishDate = it.publishDate,
                title = it.title,
                viewCount = it.viewCount.toInt()
            )
        }
        val mangaAdapter = mangaDetail!!.toMangaAdapter()
        val mangaDetailAdapter = mangaAdapter.chapterCount!!.let {
            MangaDetailAdapter(
                id = mangaAdapter.id.toLong(),
                title = mangaAdapter.title,
                description = mangaAdapter.description,
                coverImageUrl = mangaAdapter.coverImageUrl,
                genres = mangaAdapter.genres,
                averageRating = mangaAdapter.averageRating,
                ratingCount = mangaAdapter.ratingCount,
                viewCount = mangaAdapter.viewCount.toInt(),
                completed = mangaAdapter.completed,
                createdAt = mangaAdapter.createdAt,
                updatedAt = mangaAdapter.updatedAt,
                inUserFavorites = mangaAdapter.inUserFavorites,
                chapterCount = it.toInt(),
                creator = CreatorAdapter(
                    id = mangaAdapter.creator.id.toLong(),
                    username = mangaAdapter.creator.username,
                    displayName = mangaAdapter.creator.displayName
                )
            )
        }
        return MangaDetailChapterAdapter(
            chapters = mangaDetailWithChapters,
            details = mangaDetailAdapter
        )
    }
}