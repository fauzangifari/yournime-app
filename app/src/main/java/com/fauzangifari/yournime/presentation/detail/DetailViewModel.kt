package com.fauzangifari.yournime.presentation.detail

import androidx.lifecycle.ViewModel
import com.fauzangifari.core.domain.usecase.api.GetAnimeById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.fauzangifari.core.common.Resource
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getAnimeByIdUseCase: GetAnimeById
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state

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

}