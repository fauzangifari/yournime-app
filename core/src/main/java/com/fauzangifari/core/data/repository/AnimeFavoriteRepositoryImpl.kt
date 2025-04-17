package com.fauzangifari.core.data.repository

import com.fauzangifari.core.data.mapper.toEntity
import com.fauzangifari.core.data.source.local.dao.AnimeFavoriteDao
import com.fauzangifari.core.data.source.local.entity.AnimeEntity
import com.fauzangifari.core.data.source.local.entity.AnimeGenreCrossRef
import com.fauzangifari.core.domain.model.Anime
import com.fauzangifari.core.domain.model.Genre
import com.fauzangifari.core.domain.repository.AnimeFavoriteRepository
import javax.inject.Inject

class AnimeFavoriteRepositoryImpl @Inject constructor(
    private val animeFavoriteDao: AnimeFavoriteDao
) : AnimeFavoriteRepository {

    override suspend fun getAllAnime(): List<AnimeEntity> {
        return animeFavoriteDao.getAllAnime()
    }

    override suspend fun insertAnime(
        anime: Anime,
        genres: List<Genre>
    ) {
        val animeEntity = anime.toEntity()
        val genreEntities = genres.map { it.toEntity() }
        val crossRefs = genres.map { genre ->
            AnimeGenreCrossRef(
                animeId = anime.id,
                genreId = genre.malId
            )
        }

        animeFavoriteDao.insertAnime(animeEntity)
        animeFavoriteDao.insertGenre(genreEntities)
        animeFavoriteDao.insertCrossRef(crossRefs)
    }

    override suspend fun deleteAnime(malId: Int) : Int {
        animeFavoriteDao.deleteAnime(malId)
        return malId
    }
}