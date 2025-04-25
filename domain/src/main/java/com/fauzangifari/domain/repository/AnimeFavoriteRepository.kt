package com.fauzangifari.domain.repository

import com.fauzangifari.domain.model.Anime
import com.fauzangifari.domain.model.Genre

interface AnimeFavoriteRepository {

    suspend fun getAllAnime(): List<Anime>

    suspend fun insertAnime(
        anime: Anime,
        genres: List<Genre>
    )

    suspend fun deleteAnime(malId: Int) : Int

}