package com.fauzangifari.yournime.presentation.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.fauzangifari.domain.common.Resource
import com.fauzangifari.domain.model.Anime
import com.fauzangifari.domain.model.Genre
import com.fauzangifari.domain.usecase.api.GetAnimeById
import com.fauzangifari.domain.usecase.local.GetIsAnimeFavorite
import com.fauzangifari.domain.usecase.local.InsertAnimeFavorite
import kotlinx.coroutines.flow.update

class DetailViewModel(
    private val getAnimeByIdUseCase: GetAnimeById,
    private val insertAnimeFavoriteUseCase: InsertAnimeFavorite,
    private val getIsAnimeFavorite: GetIsAnimeFavorite
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state

    fun fetchIsAnimeFavorite(malId: Int) {
        viewModelScope.launch {
            getIsAnimeFavorite(malId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isAnimeFavoriteLoading = true) }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isAnimeFavoriteLoading = false,
                                isAnimeFavorite = result.data == true,
                                isAnimeFavoriteError = ""
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isAnimeFavoriteLoading = false,
                                isAnimeFavoriteError = result.message ?: "Terjadi kesalahan"
                            )
                        }
                    }
                }
            }
        }
    }

    fun fetchDetailAnime(animeId: Int){
        viewModelScope.launch{
            getAnimeByIdUseCase(
                id = animeId
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(detailAnimeLoading = true) }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                detailAnimeLoading = false,
                                detailAnime = result.data?.firstOrNull(),
                                detailAnimeError = ""
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                detailAnimeLoading = false,
                                detailAnimeError = result.message ?: "Terjadi kesalahan"
                            )
                        }
                    }
                }
            }
        }
    }

    fun insertAnimeFavorite(
        anime: Anime,
        genres: List<Genre>
    ) {
        viewModelScope.launch {
            insertAnimeFavoriteUseCase(anime, genres).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                insertAnimeLoading = true,
                            )
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                insertAnimeLoading = false,
                                insertAnime =  result.data,
                                insertAnimeError = ""
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                insertAnimeLoading = false,
                                insertAnimeError = result.message ?: "Terjadi kesalahan"
                            )
                        }
                    }
                }
            }
        }
    }

}