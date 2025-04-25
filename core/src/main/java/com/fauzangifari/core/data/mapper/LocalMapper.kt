package com.fauzangifari.core.data.mapper

import com.fauzangifari.core.data.source.local.entity.AnimeEntity
import com.fauzangifari.core.data.source.local.entity.AnimeGenreCrossRef
import com.fauzangifari.core.data.source.local.entity.GenreEntity
import com.fauzangifari.core.data.source.local.relation.AnimeWithGenres
import com.fauzangifari.domain.model.Anime
import com.fauzangifari.domain.model.Genre

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

fun Anime.toEntity() = AnimeEntity(
    malId = this.id,
    title = this.title,
    year = this.year,
    scoredBy = this.scoredBy,
    source = this.source,
    type = this.type,
    images = this.images,
    episodes = this.episodes,
    status = this.status,
    score = this.score,
    favorites = this.favorites,
    synopsis = this.synopsis,
    background = this.background,
    season = this.season,
    rank = this.rank,
    popularity = this.popularity,
    duration = this.duration,
    members = this.members,
)

fun Genre.toEntity() = this.malId.toInt().let {
    GenreEntity(
        malId = this.malId,
        name = this.name.toString(),
        type = this.type.toString(),
        url = this.url.toString(),
    )
}

fun Anime.toCrossRefs(): List<AnimeGenreCrossRef> =
    this.genre.map { genre ->
        AnimeGenreCrossRef(
            animeId = this.id,
            genreId = genre.malId
        )
    }
