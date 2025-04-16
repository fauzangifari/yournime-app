package com.fauzangifari.yournime.presentation.detail

import com.fauzangifari.core.domain.model.Anime

data class DetailState(
    val detailAnime: Anime? = null,
    val detailAnimeLoading: Boolean = false,
    val detailAnimeError: String? = null,
) {
    val detailAnimeData: Anime? get() = detailAnime
}