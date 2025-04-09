package com.monogatari.app.core.data.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.monogatari.app.core.data.local.room.dao.ChapterDao
import com.monogatari.app.core.data.local.room.dao.ChapterPageDao
import com.monogatari.app.core.data.local.room.dao.MangaDao
import com.monogatari.app.core.data.local.room.dao.MangaFavoriteDao
import com.monogatari.app.core.data.local.room.dao.MangaGenreDao
import com.monogatari.app.core.data.local.room.dao.UserDao
import com.monogatari.app.core.data.local.room.entities.ChapterEntity
import com.monogatari.app.core.data.local.room.entities.ChapterPageEntity
import com.monogatari.app.core.data.local.room.entities.MangaEntity
import com.monogatari.app.core.data.local.room.entities.MangaFavoriteEntity
import com.monogatari.app.core.data.local.room.entities.MangaGenreEntity
import com.monogatari.app.core.data.local.room.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        MangaEntity::class,
        MangaGenreEntity::class,
        MangaFavoriteEntity::class,
        ChapterEntity::class,
        ChapterPageEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MangaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun mangaDao(): MangaDao
    abstract fun mangaGenreDao(): MangaGenreDao
    abstract fun mangaFavoriteDao(): MangaFavoriteDao
    abstract fun chapterDao(): ChapterDao
    abstract fun chapterPageDao(): ChapterPageDao

    companion object {
        @Volatile
        private var INSTANCE: MangaDatabase? = null

        fun getDatabase(context: Context): MangaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MangaDatabase::class.java,
                    "manga_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}