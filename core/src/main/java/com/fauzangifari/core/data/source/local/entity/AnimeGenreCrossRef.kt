package com.fauzangifari.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "anime_genre_cross_ref",
    primaryKeys = ["animeId", "genreId"],
    foreignKeys = [
        ForeignKey(
            entity = AnimeEntity::class,
            parentColumns = ["malId"],
            childColumns = ["animeId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["malId"],
            childColumns = ["genreId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AnimeGenreCrossRef(
    val animeId: Int,
    val genreId: Int?
)