package com.fauzangifari.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "anime",
    indices = [Index(value = ["malId"], unique = true)]
)
@Parcelize
data class AnimeEntity(
    @PrimaryKey val malId: Int,
    val title: String?,
    val year: Int?,
    val scoredBy: Int?,
    val source: String?,
    val type: String?,
    val images: String?,
    val episodes: Int?,
    val status: String?,
    val score: Double?,
    val favorites: Int?,
    val synopsis: String?,
    val background: String?,
    val season: String?,
    val rank: Int?,
    val popularity: Int?,
    val duration: String?,
    val members: Int?,
) : Parcelable