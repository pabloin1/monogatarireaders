package com.monogatari.app.core.data.local.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.monogatari.app.core.data.local.room.entities.MangaEntity
import com.monogatari.app.core.data.local.room.entities.MangaFavoriteEntity
import com.monogatari.app.shared.domain.adapters.MangaFavoriteAdapter

class MangaFavoriteWithDetails {
    @Embedded
    lateinit var favorite: MangaFavoriteEntity

    @Relation(
        entity = MangaEntity::class,
        parentColumn = "mangaId",
        entityColumn = "id"
    )
    lateinit var mangaWithDetails: MangaWithDetails

    fun toMangaFavoriteAdapter() = MangaFavoriteAdapter(
        id = favorite.id,
        manga = mangaWithDetails.toMangaAdapter(),
        lastReadChapter = favorite.lastReadChapter
    )
}