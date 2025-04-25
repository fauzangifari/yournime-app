package com.fauzangifari.yournime.presentation.search

import com.fauzangifari.domain.model.Anime

data class SearchState(
    val searchAnime: List<Anime> = emptyList(),
    val searchLoading: Boolean = false,
    val searchError: String? = null
) {
    val searchAnimeList: List<Anime> get() = searchAnime
}