package com.fauzangifari.core.data.repository

import com.fauzangifari.core.data.source.remote.response.AnimeResponse
import com.fauzangifari.core.data.source.remote.retrofit.ApiService
import com.fauzangifari.core.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AnimeRepository {

    override suspend fun getAllAnime(): AnimeResponse {
        return apiService.getAllAnime()
    }

    override suspend fun getTopAnime(
        type: String?,
        filter: String?,
        rating: String?
    ): AnimeResponse {
        return apiService.getTopAnime(type = type, filter = filter, rating = rating)
    }
}