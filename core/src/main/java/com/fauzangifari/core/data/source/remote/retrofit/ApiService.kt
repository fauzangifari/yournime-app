package com.fauzangifari.core.data.source.remote.retrofit

import com.fauzangifari.core.data.source.remote.response.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("anime")
    suspend fun getAllAnime(): AnimeResponse

    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("type") type: String? = null,
        @Query("filter") filter: String? = null,
        @Query("rating") rating: String? = null
    ): AnimeResponse

    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") query: String,
        @Query("sort") sort: String = "asc"
    ): AnimeResponse
}