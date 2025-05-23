package com.fauzangifari.yournime.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzangifari.domain.common.Resource
import com.fauzangifari.domain.common.model.TopAnimeFilter
import com.fauzangifari.domain.usecase.api.GetAnimeUpcoming
import com.fauzangifari.domain.usecase.api.GetTopAnime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel (
    private val getTopAnimeUseCase: GetTopAnime,
    private val getAnimeUpcomingUseCase: GetAnimeUpcoming,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        fetchTopAnime()
        fetchAnimeUpcoming()
        fetchAiringAnime()
    }

    private fun fetchAiringAnime(){
        viewModelScope.launch {
            getTopAnimeUseCase(
                filter = TopAnimeFilter.AIRING.toString()
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(airingAnimeLoading = true) }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                airingAnimeLoading = false,
                                airingAnime = result.data ?: emptyList(),
                                airingAnimeError = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                airingAnimeLoading = false,
                                airingAnimeError = result.message ?: "Terjadi kesalahan"
                            )
                        }
                    }
                }
            }
        }
    }

    private fun fetchTopAnime() {
        viewModelScope.launch {
            getTopAnimeUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(topAnimeLoading = true) }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                topAnimeLoading = false,
                                topAnime = result.data ?: emptyList(),
                                topAnimeError = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                topAnimeLoading = false,
                                topAnimeError = result.message ?: "Terjadi kesalahan"
                            )
                        }
                    }
                }
            }
        }
    }

    private fun fetchAnimeUpcoming() {
        viewModelScope.launch {
            getAnimeUpcomingUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(upcomingAnimeLoading = true) }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                upcomingAnimeLoading = false,
                                upcomingAnime = result.data ?: emptyList(),
                                upcomingAnimeError = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                upcomingAnimeLoading = false,
                                upcomingAnimeError = result.message ?: "Terjadi kesalahan"
                            )
                        }
                    }
                }
            }
        }
    }
}
