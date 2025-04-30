package com.fauzangifari.yournime.presentation.detail

import com.fauzangifari.domain.model.Anime

data class DetailState(
    val detailAnime: Anime? = null,
    val insertAnime: Anime? = null,

    val detailAnimeLoading: Boolean = false,
    val insertAnimeLoading: Boolean = false,

    val detailAnimeError: String? = null,
    val insertAnimeError: String? = null
) {
    val detailAnimeData: Anime? get() = detailAnime
    val insertAnimeData: Anime? get() = insertAnime
}