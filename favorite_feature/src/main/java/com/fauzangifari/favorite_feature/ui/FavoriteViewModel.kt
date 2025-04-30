package com.fauzangifari.favorite_feature.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzangifari.domain.common.Resource
import com.fauzangifari.domain.usecase.local.GetAllAnimeFavorite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel (
    private val getAnimeFavoriteUseCase: GetAllAnimeFavorite
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState> = _state

    init {
        fetchAnimeFavorite()
    }

    private fun fetchAnimeFavorite(){
        viewModelScope.launch {
            getAnimeFavoriteUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                favoriteAnimeLoading = true,
                            )
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                favoriteAnimeLoading = false,
                                favoriteAnime = result.data ?: emptyList(),
                                favoriteAnimeError = ""
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                favoriteAnimeLoading = false,
                                favoriteAnimeError = result.message ?: "Terjadi kesalahan"
                            )
                        }
                    }
                }
            }
        }
    }
}
