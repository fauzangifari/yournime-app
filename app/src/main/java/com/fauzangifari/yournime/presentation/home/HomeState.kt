package com.fauzangifari.yournime.presentation.home

import com.fauzangifari.core.domain.model.Anime

data class HomeState(
    val topAnime: List<Anime> = emptyList(),
    val upcomingAnime: List<Anime> = emptyList(),
    val airingAnime: List<Anime> = emptyList(),
    val searchAnime: List<Anime> = emptyList(),

    val topAnimeLoading: Boolean = false,
    val upcomingAnimeLoading: Boolean = false,
    val airingAnimeLoading: Boolean = false,
    val searchLoading: Boolean = false,

    val topAnimeError: String? = null,
    val upcomingAnimeError: String? = null,
    val airingAnimeError: String? = null,
    val searchError: String? = null
) {
    val topAnimeList: List<Anime> get() = topAnime
    val upcomingAnimeList: List<Anime> get() = upcomingAnime
    val airingAnimeList: List<Anime> get() = airingAnime
    val searchAnimeList: List<Anime> get() = searchAnime
}
