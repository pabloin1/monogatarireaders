import android.util.Log
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.monogatari.app.core.data.local.room.database.MangaDatabase
import com.monogatari.app.core.data.local.room.entities.ChapterEntity
import com.monogatari.app.core.data.local.room.entities.ChapterPageEntity
import com.monogatari.app.core.data.local.room.entities.MangaFavoriteEntity
import com.monogatari.app.core.data.local.room.relations.MangaFavoriteWithDetails
import com.monogatari.app.manga_detail.data.services.GetMangaChaptersService
import com.monogatari.app.manga_chapter.data.services.GetMangaChapterByChapterIdService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

class SyncMangaWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val mangaFavoriteDao = MangaDatabase.getDatabase(context).mangaFavoriteDao()
    private val chapterDao = MangaDatabase.getDatabase(context).chapterDao()
    private val chapterPageDao = MangaDatabase.getDatabase(context).chapterPageDao()
    private val chapterService = GetMangaChaptersService()
    private val chapterDetailService = GetMangaChapterByChapterIdService()

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            Log.d("SyncMangaWorker", "Starting the sync process...")

            val favoriteMangas = getFavoriteMangasFromRoom()

            if (favoriteMangas.isEmpty()) {
                Log.d("SyncMangaWorker", "No favorite mangas found.")
            }

            for (manga in favoriteMangas) {
                val mangaId = manga.favorite.mangaId.toInt()
                Log.d("SyncMangaWorker", "Fetching chapters for manga ID: $mangaId")

                // Get manga details from the API
                val chapterResult = chapterService.getMangaChapters(mangaId)
                chapterResult.fold(
                    onSuccess = { chapters ->
                        Log.d("SyncMangaWorker", "Successfully fetched chapters for manga ID: $mangaId")
                        val mangaDetail = manga.mangaWithDetails.manga
                        chapters.forEach { chapter ->
                            Log.d("SyncMangaWorker", "Saving chapter ${chapter.chapterNumber} for manga ${mangaDetail.title}")
                            val chapterEntity = ChapterEntity(
                                mangaTitle = mangaDetail.title,
                                title = chapter.title,
                                chapterNumber = chapter.chapterNumber.toLong(),
                                description = mangaDetail.description,
                                publishDate = chapter.publishDate,
                                viewCount = chapter.viewCount.toLong(),
                                mangaId = mangaId.toLong(),
                                id = chapter.id.toLong(),
                            )
                            Log.d("SyncMangaWorker", "Chapter details: ${chapterEntity}")

                            chapterDao.insert(chapterEntity)
                        }
                    },
                    onFailure = { error ->
                        Log.e("SyncMangaWorker", "Error fetching chapters: ${error.message}")
                        error.printStackTrace()
                    }
                )

                // Fetch chapter details and images
                chapterResult.onSuccess { chapters ->
                    Log.e("SyncMangaWorker", "Fetching chapter details for manga ID: $chapters")
                    chapters.forEach { chapter ->
                        Log.d("SyncMangaWorker", "Fetching details for chapter ${chapter.id}")
                        val detailResult = chapterDetailService.getMangaChapter(chapter.id)
                        detailResult.onSuccess { chapterDetail ->
                            val imageUrls = chapterDetail.pages
                            imageUrls.forEachIndexed { index, imageUrl ->
                                Log.d("SyncMangaWorker", "Downloading image for chapter ${chapter.id}, page $index")
                                val path = downloadImage(imageUrl, mangaId, chapter.id, index)
                                if (path != null) {
                                    chapterPageDao.insert(
                                        ChapterPageEntity(
                                            chapterId = chapter.id.toLong(),
                                            pageUrl = imageUrl,
                                            pageIndex = index,
                                            localPath = path,
                                            isDownloaded = true
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Log.d("SyncMangaWorker", "Sync process completed successfully.")
            Result.success()
        } catch (e: Exception) {
            Log.e("SyncMangaWorker", "Error in sync process: ${e.message}")
            e.printStackTrace()
            Result.retry()
        }
    }

    private fun downloadImage(imageUrl: String, mangaId: Int, chapterId: Int, index: Int) : String? {
        try {
            val imageBytes = URL(imageUrl).readBytes()
            val folder = File(context.filesDir, "offline/manga_$mangaId/chapter_$chapterId")
            if (!folder.exists()) folder.mkdirs()

            val file = File(folder, "page_$index.jpg")
            file.writeBytes(imageBytes)
            Log.d("SyncMangaWorker", "Downloaded image: page_$index.jpg for manga $mangaId, chapter $chapterId")
            return file.absolutePath
        } catch (e: Exception) {
            Log.e("SyncMangaWorker", "Error downloading image: ${e.message}")
            e.printStackTrace()
            return null
        }
    }

    private suspend fun getFavoriteMangasFromRoom(): List<MangaFavoriteWithDetails> {
        return mangaFavoriteDao.getAllFavoritesWithDetails()
    }
}
