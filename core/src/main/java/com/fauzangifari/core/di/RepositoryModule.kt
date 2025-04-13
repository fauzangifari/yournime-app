package com.fauzangifari.core.di

import com.fauzangifari.core.data.repository.AnimeRepositoryImpl
import com.fauzangifari.core.data.source.remote.retrofit.ApiService
import com.fauzangifari.core.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAnimeRepository(
        apiService: ApiService
    ) : AnimeRepository = AnimeRepositoryImpl(apiService = apiService)

}