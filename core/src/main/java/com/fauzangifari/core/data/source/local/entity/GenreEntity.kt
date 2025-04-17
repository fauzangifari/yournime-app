package com.fauzangifari.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "genre",
    indices = [Index(value = ["malId"], unique = true)]
)
@Parcelize
data class GenreEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val malId: Int,
    val name: String?,
    val type: String?,
    val url: String?
) : Parcelable