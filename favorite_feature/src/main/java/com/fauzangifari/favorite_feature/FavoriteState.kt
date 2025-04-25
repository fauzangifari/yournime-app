package com.fauzangifari.favorite_feature

import com.fauzangifari.domain.model.Anime

data class FavoriteState(
    val favoriteAnime: Anime? = null,
    val favoriteAnimeLoading: Boolean = false,
    val favoriteAnimeError: String? = null,
) {
    val favoriteAnimeData: Anime? get() = favoriteAnime
}