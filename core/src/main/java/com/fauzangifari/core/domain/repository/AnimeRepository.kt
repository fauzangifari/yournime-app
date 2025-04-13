package com.fauzangifari.core.domain.repository

import com.fauzangifari.core.data.source.remote.response.AnimeResponse

interface AnimeRepository {
    suspend fun getAllAnime(): AnimeResponse

    suspend fun getTopAnime(
        type: String? = null,
        filter: String? = null,
        rating: String? = null
    ): AnimeResponse
}