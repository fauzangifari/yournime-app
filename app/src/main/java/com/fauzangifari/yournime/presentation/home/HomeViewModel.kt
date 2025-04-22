package com.fauzangifari.yournime.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzangifari.core.common.Resource
import com.fauzangifari.core.common.model.TopAnimeFilter
import com.fauzangifari.core.domain.usecase.api.GetAnimeSearch
import com.fauzangifari.core.domain.usecase.api.GetAnimeUpcoming
import com.fauzangifari.core.domain.usecase.api.GetTopAnime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopAnimeUseCase: GetTopAnime,
    private val getAnimeUpcomingUseCase: GetAnimeUpcoming,
    private val getAnimeSearchUseCase: GetAnimeSearch
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        fetchTopAnime()
        fetchAnimeUpcoming()
        fetchAiringAnime()
    }

    fun fetchAnimeBySearch(
        query: String
    ) {
        viewModelScope.launch {
            getAnimeSearchUseCase(
                query = query
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(searchLoading = true) }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                searchLoading = false,
                                searchAnime = result.data ?: emptyList(),
                                searchError = ""
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                searchLoading = false,
                                searchError = result.message ?: "Terjadi kesalahan"
                            )
                        }
                    }
                }

            }
        }
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
                                airingAnimeError = ""
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
                                topAnimeError = ""
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
                                upcomingAnimeError = ""
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
