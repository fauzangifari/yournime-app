package com.fauzangifari.yournime.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzangifari.core.common.Resource
import com.fauzangifari.core.domain.usecase.GetTopAnime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getTopAnimeUseCase: GetTopAnime
) : ViewModel() {

    private var isLoaded = false

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        getTopAnime()
    }

    private fun getTopAnime(
        type: String? = null,
        filter: String? = null,
        rating: String? = null
    ) {
        if (isLoaded) return

        viewModelScope.launch {
            getTopAnimeUseCase(
                type = type,
                filter = filter,
                rating = rating
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        isLoaded = true
                        _state.update {
                            it.copy(
                                isLoading = false,
                                data = result.data ?: emptyList(),
                                error = ""
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.message ?: "Terjadi kesalahan"
                            )
                        }
                    }
                }
            }
        }
    }
}
