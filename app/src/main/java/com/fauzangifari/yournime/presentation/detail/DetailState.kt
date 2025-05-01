package com.fauzangifari.yournime.presentation.detail

import com.fauzangifari.domain.model.Anime

data class DetailState(
    val detailAnime: Anime? = null,
    val insertAnime: Anime? = null,
    val isAnimeFavorite: Boolean = false,
    val deleteAnimeFavorite: Int = 0,

    val detailAnimeLoading: Boolean = false,
    val insertAnimeLoading: Boolean = false,
    val isAnimeFavoriteLoading: Boolean = false,
    val deleteAnimeFavoriteLoading: Boolean = false,

    val detailAnimeError: String? = null,
    val insertAnimeError: String? = null,
    val isAnimeFavoriteError: String? = null,
    val deleteAnimeFavoriteError: String? = null,
) {
    val detailAnimeData: Anime? get() = detailAnime
    val insertAnimeData: Anime? get() = insertAnime
    val isAnimeFavoriteData: Boolean get() = isAnimeFavorite
    val deleteAnimeFavoriteData: Int get() = deleteAnimeFavorite
}