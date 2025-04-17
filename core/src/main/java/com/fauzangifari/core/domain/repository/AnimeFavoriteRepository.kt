package com.fauzangifari.core.domain.repository

import com.fauzangifari.core.data.source.local.entity.AnimeEntity
import com.fauzangifari.core.data.source.local.entity.AnimeGenreCrossRef
import com.fauzangifari.core.data.source.local.entity.GenreEntity

interface AnimeFavoriteRepository {

    suspend fun getAllAnime(): List<AnimeEntity>

    suspend fun insertAnime(
        malId: Int,
        title: String,
        year: Int,
        scoredBy: Int,
        source: String,
        type: String,
        images: String,
        episodes: Int,
        status: String,
        score: Double,
        favorites: Int,
        synopsis: String,
        background: String,
        season: String,
        genreEntity: GenreEntity,
        rank: Int,
        popularity: Int,
        duration: String,
        members: Int
    ) : AnimeEntity

    suspend fun insertGenre(
        malId: Int,
        name: String,
        type: String,
        url: String
    ) : GenreEntity

    suspend fun insertCrossRef(
        animeMalId: Int,
        genreMalId: Int
    ) : AnimeGenreCrossRef

    suspend fun deleteAnime(malId: Int) : Int

}