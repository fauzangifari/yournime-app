package com.fauzangifari.favorite_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzangifari.domain.common.Resource
import com.fauzangifari.domain.model.Anime
import com.fauzangifari.domain.model.Genre
import com.fauzangifari.domain.usecase.local.InsertAnimeFavorite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
class FavoriteViewModel (
    private val insertAnimeFavoriteUseCase: InsertAnimeFavorite
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState> = _state

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
                                favoriteAnimeLoading = true,
                                favoriteAnimeError = null
                            )
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                favoriteAnimeLoading = false,
                                favoriteAnime = result.data
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
