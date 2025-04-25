package com.fauzangifari.domain.repository

import com.fauzangifari.domain.model.Anime

interface AnimeRepository {
    suspend fun getAllAnime(): List<Anime>

    suspend fun getTopAnime(
        type: String? = null,
        filter: String? = null,
        rating: String? = null
    ): List<Anime>

    suspend fun getAnimeSearch(
        query: String,
        sort: String = "asc"
    ): List<Anime>

    suspend fun getAnimeById(
        animeId: Int
    ): List<Anime>

    suspend fun getAnimeUpcoming(): List<Anime>
}