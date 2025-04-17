package com.fauzangifari.core.domain.usecase

import com.fauzangifari.core.common.Resource
import com.fauzangifari.core.domain.model.Anime
import com.fauzangifari.core.domain.repository.AnimeRepository
import com.fauzangifari.core.data.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAnimeSearch @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    operator fun invoke(
        query: String,
        sort: String = "asc"
    ) : Flow<Resource<List<Anime>>> = flow {
        try {
            emit(Resource.Loading())
            val response = animeRepository.getAnimeSearch(
                query = query,
                sort = sort
            )
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