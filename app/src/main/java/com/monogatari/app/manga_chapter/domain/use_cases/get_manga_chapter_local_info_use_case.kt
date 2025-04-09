package com.monogatari.app.manga_chapter.domain.use_cases

import android.content.Context
import com.monogatari.app.core.data.local.room.database.MangaDatabase
import com.monogatari.app.manga_chapter.domain.adapters.MangaChapterAdapter

class GetMangaChapterLocalInfoUseCase(context: Context){
    private val _chapterPageDao = MangaDatabase.getDatabase(context).chapterPageDao()
    private val _chapterDao = MangaDatabase.getDatabase(context).chapterDao()

    suspend fun execute(chapterId : Int) : MangaChapterAdapter{
        val mangaChapterPages = _chapterPageDao.getPagesByChapterId(chapterId)
        val mangaChapter = _chapterDao.getChapterById(chapterId)
        val pages = mangaChapterPages.map { it?.localPath ?: "" }
        val mangaChapterAdapter = MangaChapterAdapter(
            id = mangaChapter?.chapterNumber?.toLong() ?: 0,
            mangaId = mangaChapter?.mangaId ?: 0,
            mangaTitle = mangaChapter?.mangaTitle ?: "",
            title = mangaChapter?.title ?: "",
            chapterNumber = mangaChapter?.chapterNumber ?: 0,
            description = mangaChapter?.description ?: "",
            publishDate = mangaChapter?.publishDate ?: "",
            pages = pages,
            viewCount = mangaChapter?.viewCount ?: 0
        )

        return mangaChapterAdapter
    }
}