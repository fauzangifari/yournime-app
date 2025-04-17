package com.fauzangifari.core.di

import android.content.Context
import androidx.room.Room
import com.fauzangifari.core.data.source.local.Database
import com.fauzangifari.core.data.source.local.dao.AnimeFavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java as Class,
            "anime_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideAnimeFavoriteDao(
        database: Database
    ) : AnimeFavoriteDao {
        return database.animeFavoriteDao
    }

}