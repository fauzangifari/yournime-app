package com.fauzangifari.core.domain.repository

import com.fauzangifari.core.data.source.remote.response.AnimeResponse
import com.fauzangifari.core.data.source.remote.response.DetailAnimeResponse

interface AnimeRepository {
    suspend fun getAllAnime(): AnimeResponse

    suspend fun getTopAnime(
        type: String? = null,
        filter: String? = null,
        rating: String? = null
    ): AnimeResponse

    suspend fun getAnimeSearch(
        query: String,
        sort: String = "asc"
    ): AnimeResponse

    suspend fun getAnimeById(
        animeId: Int
    ): DetailAnimeResponse

    suspend fun getAnimeUpcoming(): AnimeResponse
}