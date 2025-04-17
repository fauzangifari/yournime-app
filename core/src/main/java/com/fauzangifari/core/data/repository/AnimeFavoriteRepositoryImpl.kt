package com.fauzangifari.core.data.repository

import com.fauzangifari.core.data.source.local.dao.AnimeFavoriteDao
import com.fauzangifari.core.data.source.local.entity.AnimeEntity
import com.fauzangifari.core.data.source.local.entity.AnimeGenreCrossRef
import com.fauzangifari.core.data.source.local.entity.GenreEntity
import com.fauzangifari.core.domain.repository.AnimeFavoriteRepository
import javax.inject.Inject

class AnimeFavoriteRepositoryImpl @Inject constructor(
    private val animeFavoriteDao: AnimeFavoriteDao
) : AnimeFavoriteRepository {

    override suspend fun getAllAnime(): List<AnimeEntity> {
        return animeFavoriteDao.getAllAnime()
    }

    override suspend fun insertAnime(
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
    ): AnimeEntity {
        val animeEntity = AnimeEntity(
            malId = malId,
            title = title,
            year = year,
            scoredBy = scoredBy,
            source = source,
            type = type,
            images = images,
            episodes = episodes,
            status = status,
            score = score,
            favorites = favorites,
            synopsis = synopsis,
            background = background,
            season = season,
            genreEntity = genreEntity,
            rank = rank,
            popularity = popularity,
            duration = duration,
            members = members
        )
        animeFavoriteDao.insertAnime(animeEntity)
        return animeEntity
    }

    override suspend fun insertGenre(
        malId: Int,
        name: String,
        type: String,
        url: String
    ): GenreEntity {
        val genreEntity = GenreEntity(
            malId = malId,
            name = name,
            type = type,
            url = url
        )
        animeFavoriteDao.insertGenre(genreEntity)
        return genreEntity
    }

    override suspend fun insertCrossRef(
        animeMalId: Int,
        genreMalId: Int
    ) : AnimeGenreCrossRef {
        val crossRef = AnimeGenreCrossRef(
            animeId = animeMalId,
            genreId = genreMalId
        )
        animeFavoriteDao.insertCrossRef(crossRef)
        return crossRef
    }

    override suspend fun deleteAnime(malId: Int) : Int {
        animeFavoriteDao.deleteAnime(malId)
        return malId
    }
}