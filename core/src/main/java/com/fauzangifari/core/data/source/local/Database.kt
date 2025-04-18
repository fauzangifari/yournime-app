package com.fauzangifari.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fauzangifari.core.data.source.local.dao.AnimeFavoriteDao
import com.fauzangifari.core.data.source.local.entity.AnimeEntity
import com.fauzangifari.core.data.source.local.entity.AnimeGenreCrossRef
import com.fauzangifari.core.data.source.local.entity.GenreEntity

@Database(
    entities = [AnimeEntity::class, GenreEntity::class, AnimeGenreCrossRef::class],
    version = 1
)
abstract class Database : RoomDatabase(){

    abstract val animeFavoriteDao: AnimeFavoriteDao

}