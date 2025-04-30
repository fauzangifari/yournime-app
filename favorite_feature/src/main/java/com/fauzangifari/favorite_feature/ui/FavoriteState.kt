package com.fauzangifari.favorite_feature.ui

import com.fauzangifari.domain.model.Anime

data class FavoriteState(
    val favoriteAnime: List<Anime>? = null,
    val favoriteAnimeLoading: Boolean = false,
    val favoriteAnimeError: String? = null,
) {
    val favoriteAnimeData: List<Anime>? get() = favoriteAnime
}