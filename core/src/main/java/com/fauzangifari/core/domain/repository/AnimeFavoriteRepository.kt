package com.fauzangifari.core.domain.repository

import com.fauzangifari.core.data.source.local.entity.AnimeEntity
import com.fauzangifari.core.domain.model.Anime
import com.fauzangifari.core.domain.model.Genre

interface AnimeFavoriteRepository {

    suspend fun getAllAnime(): List<AnimeEntity>

    suspend fun insertAnime(
        anime: Anime,
        genres: List<Genre>
    )

    suspend fun deleteAnime(malId: Int) : Int

}