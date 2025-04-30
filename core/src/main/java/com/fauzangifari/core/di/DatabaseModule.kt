package com.fauzangifari.core.di

import androidx.room.Room
import com.fauzangifari.core.data.source.local.Database
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            "anime_db"
        ).build()
    }

    factory {
        get<Database>().animeFavoriteDao
    }
}
