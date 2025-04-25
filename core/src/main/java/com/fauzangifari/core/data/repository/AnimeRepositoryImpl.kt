package com.fauzangifari.core.data.repository

import com.fauzangifari.core.data.mapper.toDomain
import com.fauzangifari.core.data.source.remote.retrofit.ApiService
import com.fauzangifari.domain.model.Anime
import com.fauzangifari.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AnimeRepository {

    override suspend fun getAllAnime(): List<Anime> {
        val response = apiService.getAllAnime()
        return response.data?.mapNotNull { it?.toDomain() } ?: emptyList()
    }

    override suspend fun getTopAnime(
        type: String?,
        filter: String?,
        rating: String?
    ): List<Anime> {
        val response = apiService.getTopAnime(type, filter, rating)
        return response.data?.mapNotNull { it?.toDomain() } ?: emptyList()
    }

    override suspend fun getAnimeSearch(
        query: String,
        sort: String
    ): List<Anime> {
        val response = apiService.getAnimeSearch(query = query, sort = sort)
        return response.data?.mapNotNull { it?.toDomain() } ?: emptyList()
    }

    override suspend fun getAnimeById(animeId: Int): List<Anime> {
        val response = apiService.getAnimeById(animeId)
        return listOfNotNull(response.data?.toDomain())
    }

    override suspend fun getAnimeUpcoming(): List<Anime> {
        val response = apiService.getAnimeUpcoming()
        return response.data?.mapNotNull { it?.toDomain() } ?: emptyList()
    }
}