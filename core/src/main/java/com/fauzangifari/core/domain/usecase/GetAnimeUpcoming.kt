package com.fauzangifari.core.domain.usecase

import com.fauzangifari.core.common.Resource
import com.fauzangifari.core.domain.model.Anime
import com.fauzangifari.core.domain.repository.AnimeRepository
import com.fauzangifari.core.utils.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAnimeUpcoming @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    operator fun invoke() : Flow<Resource<List<Anime>>> = flow {
        try {
            emit(Resource.Loading())
            val response = animeRepository.getAnimeUpcoming()
            val animeList = response.data?.mapNotNull {
                it?.toDomain()
            } ?: emptyList()
            emit(Resource.Success(animeList))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Terjadi kesalahan yang tidak terduga"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Terjadi kesalahan jaringan"))
        } catch (e: IOException) {
            emit(Resource.Error("Tidak dapat menghubungi server. Periksa koneksi internet anda."))
        }
    }
}