package com.fauzangifari.core.domain.model

data class Anime(
    val id: Int,
    val title: String,
    val year: Int,
    val scoredBy: Int,
    val source: String,
    val type: String,
    val images: String,
    val episodes: Int,
    val status: String,
    val score: Double,
    val favorites: Int,
    val synopsis: String,
    val background: String,
    val season: String,
    val genre: List<Genre>,
    val rank: Int,
    val popularity: Int,
    val duration: String,
    val members : Int,
)