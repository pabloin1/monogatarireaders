package com.monogatari.app.core.utils.images

import android.content.Context
import android.util.Log
import com.monogatari.app.core.data.local.room.database.MangaDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL

object ImageDownloader {
    private const val TAG = "ImageDownloader"

    suspend fun downloadChapterImages(context: Context, chapterId: Int, pageUrls: List<String>) = withContext(
        Dispatchers.IO) {
        val database = MangaDatabase.getDatabase(context)
        val chapterPageDao = database.chapterPageDao()

        val directory = File(context.filesDir, "manga_chapters/$chapterId")
        if (!directory.exists()) {
            directory.mkdirs()
        }

        pageUrls.forEachIndexed { index, url ->
            try {
                val fileName = "page_${index + 1}.jpg"
                val file = File(directory, fileName)

                URL(url).openStream().use { input ->
                    FileOutputStream(file).use { output ->
                        input.copyTo(output)
                    }
                }

                // We update the local path in the database
                val localPath = file.absolutePath
                chapterPageDao.updatePageLocalPath(chapterId, index, localPath, true)

                Log.d(TAG, "Downloaded image from $url to $localPath")
            } catch (e: Exception) {
                Log.e(TAG, "Error downloading image from $url: ${e.message}")
            }
        }
    }
}