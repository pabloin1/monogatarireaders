package com.monogatari.app.core.data.local.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.monogatari.app.core.data.local.room.entities.MangaEntity
import com.monogatari.app.core.data.local.room.entities.MangaGenreEntity
import com.monogatari.app.core.data.local.room.entities.UserEntity
import com.monogatari.app.shared.domain.adapters.MangaAdapter
import com.monogatari.app.shared.domain.adapters.UserAdapter

class MangaWithDetails{
    @Embedded
    lateinit var manga: MangaEntity

    @Relation(
        parentColumn = "creatorId",
        entityColumn = "id"
    )
    lateinit var creator: UserEntity

    @Relation(
        parentColumn = "id",
        entityColumn = "mangaId",
    )
    lateinit var genres: List<MangaGenreEntity>

    fun toMangaAdapter() = MangaAdapter(
        id = manga.id,
        title = manga.title,
        description = manga.description,
        coverImageUrl = manga.coverImageUrl,
        creator = UserAdapter(
            id = creator.id,
            username = creator.username,
            displayName = creator.displayName,
            profileImageUrl = creator.profileImageUrl
        ),
        createdAt = manga.createdAt,
        updatedAt = manga.updatedAt,
        completed = manga.completed,
        genres = genres.map { it.genre },
        viewCount = manga.viewCount,
        averageRating = manga.averageRating,
        ratingCount = manga.ratingCount,
        chapterCount = manga.chapterCount,
        inUserFavorites = manga.inUserFavorites
    )
}