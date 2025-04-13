package com.fauzangifari.core.utils

import com.fauzangifari.core.data.source.remote.response.DataItem
import com.fauzangifari.core.domain.model.Anime

fun DataItem.toDomain(): Anime {
    return Anime(
        id = this.malId ?: 0,
        title = this.title.orEmpty(),
        year = this.year ?: 0,
        scoredBy = this.scoredBy ?: 0,
        source = this.source.orEmpty(),
        type = this.type.orEmpty(),
        images = this.images?.jpg?.imageUrl.orEmpty(),
        episodes = this.episodes ?: 0,
        status = this.status.orEmpty(),
        score = this.score ?: 0.0,
        favorites = this.favorites ?: 0,
        synopsis = this.synopsis.orEmpty(),
        background = this.background.orEmpty(),
        season = this.season.orEmpty(),
        genre = this.genres?.map { it?.name.orEmpty() } ?: emptyList()
    )
}
