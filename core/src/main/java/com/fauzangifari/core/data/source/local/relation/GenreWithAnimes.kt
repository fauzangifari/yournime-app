package com.fauzangifari.core.data.source.local.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.fauzangifari.core.data.source.local.entity.AnimeEntity
import com.fauzangifari.core.data.source.local.entity.AnimeGenreCrossRef
import com.fauzangifari.core.data.source.local.entity.GenreEntity

data class GenreWithAnimes (
    @Embedded val genre: GenreEntity,

    @Relation(
        parentColumn = "malId",
        entityColumn = "malId",
        associateBy = Junction(
            value = AnimeGenreCrossRef::class,
            parentColumn = "genreId",
            entityColumn = "animeId"
        )
    )
    val animes: List<AnimeEntity>
)