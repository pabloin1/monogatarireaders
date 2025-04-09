package com.monogatari.app.core.data.local.room.relations

import androidx.room.Relation
import com.monogatari.app.core.data.local.room.entities.MangaFavoriteEntity
import com.monogatari.app.profile.domain.adapters.LibraryAdapter

class Library {
    @Relation(
        entity = MangaFavoriteEntity::class,
        parentColumn = "id",
        entityColumn = "id"
    )
    lateinit var favorites: List<MangaFavoriteWithDetails>

    fun toLibraryAdapter(chaptersRead: Int) = LibraryAdapter(
        library = favorites.map { it.toMangaFavoriteAdapter() },
        libraryCount = favorites.size,
        chaptersRead = chaptersRead
    )
}