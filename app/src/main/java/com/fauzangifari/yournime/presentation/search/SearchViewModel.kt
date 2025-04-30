package com.fauzangifari.yournime.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzangifari.domain.common.Resource
import com.fauzangifari.domain.usecase.api.GetAnimeSearch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getAnimeSearchUseCase: GetAnimeSearch
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state

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

}