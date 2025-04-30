package com.fauzangifari.domain.usecase.local

import com.fauzangifari.domain.common.Resource
import com.fauzangifari.domain.model.Anime
import com.fauzangifari.domain.repository.AnimeFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAllAnimeFavorite(
    private val animeFavoriteRepository: AnimeFavoriteRepository
) {
    operator fun invoke(): Flow<Resource<List<Anime>>> = flow {
        try {
            emit(Resource.Loading())
            val animeList = animeFavoriteRepository.getAllAnime()
            emit(Resource.Success(animeList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Terjadi kesalahan jaringan"))
        } catch (e: IOException) {
            emit(Resource.Error("Tidak dapat menghubungi server. Periksa koneksi internet anda."))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Terjadi kesalahan yang tidak terduga"))
        }
    }
}