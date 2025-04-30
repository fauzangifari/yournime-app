package com.fauzangifari.core.data.repository

import com.fauzangifari.core.data.mapper.toDomain
import com.fauzangifari.core.data.mapper.toEntity
import com.fauzangifari.core.data.source.local.dao.AnimeFavoriteDao
import com.fauzangifari.core.data.source.local.entity.AnimeGenreCrossRef
import com.fauzangifari.core.data.source.local.entity.GenreEntity
import com.fauzangifari.domain.model.Anime
import com.fauzangifari.domain.model.Genre
import com.fauzangifari.domain.repository.AnimeFavoriteRepository

class AnimeFavoriteRepositoryImpl(
    private val animeFavoriteDao: AnimeFavoriteDao
) : AnimeFavoriteRepository {

    override suspend fun getAllAnime(): List<Anime> {
        return animeFavoriteDao.getAllAnimeWithGenres().map { it.toDomain() }
    }

    override suspend fun insertAnime(
        anime: Anime,
        genres: List<Genre>
    ) {
        val animeEntity = anime.toEntity()
        val genreEntities: List<GenreEntity> = genres.map { it.toEntity() }
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

    override suspend fun isFavoriteAnime(malId: Int): Boolean {
        return animeFavoriteDao.isAnimeFavorite(malId)
    }
}
