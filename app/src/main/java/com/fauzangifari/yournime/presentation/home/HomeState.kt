package com.fauzangifari.yournime.presentation.home

import com.fauzangifari.core.domain.model.Anime

data class HomeState(
    val isLoading: Boolean = false,
    val data: List<Anime> = emptyList(),
    val error: String? = null
) {
    val animeList: List<Anime> get() = data
}