package com.fauzangifari.core.data.mapper

import com.fauzangifari.core.data.source.local.relation.AnimeWithGenres
import com.fauzangifari.core.domain.model.Anime
import com.fauzangifari.core.domain.model.Genre

fun AnimeWithGenres.toDomain(): Anime {
    return Anime(
        id = animeEntity.malId,
        title = animeEntity.title.orEmpty(),
        year = animeEntity.year ?: 0,
        scoredBy = animeEntity.scoredBy ?: 0,
        source = animeEntity.source.orEmpty(),
        type = animeEntity.type.orEmpty(),
        images = animeEntity.images.orEmpty(),
        episodes = animeEntity.episodes ?: 0,
        status = animeEntity.status.orEmpty(),
        score = animeEntity.score ?: 0.0,
        favorites = animeEntity.favorites ?: 0,
        synopsis = animeEntity.synopsis.orEmpty(),
        background = animeEntity.background.orEmpty(),
        season = animeEntity.season.orEmpty(),
        genre = genreEntities.map {
            Genre(
                malId = it.malId,
                name = it.name,
                type = it.type,
                url = it.url,
            )
        },
        rank = animeEntity.rank ?: 0,
        popularity = animeEntity.popularity ?: 0,
        duration = animeEntity.duration.orEmpty(),
        members = animeEntity.members ?: 0,
    )
}