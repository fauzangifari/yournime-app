package com.fauzangifari.domain.usecase.api

import com.fauzangifari.domain.model.Anime
import com.fauzangifari.domain.repository.AnimeRepository
import com.fauzangifari.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAnimeUpcoming(
    private val animeRepository: AnimeRepository
) {
    operator fun invoke() : Flow<Resource<List<Anime>>> = flow {
        try {
            emit(Resource.Loading())
            val response = animeRepository.getAnimeUpcoming()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Terjadi kesalahan yang tidak terduga"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Terjadi kesalahan jaringan"))
        } catch (e: IOException) {
            emit(Resource.Error("Tidak dapat menghubungi server. Periksa koneksi internet anda."))
        }
    }
}