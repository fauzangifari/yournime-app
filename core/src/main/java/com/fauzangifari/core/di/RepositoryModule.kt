package com.fauzangifari.core.di

import com.fauzangifari.core.data.repository.AnimeFavoriteRepositoryImpl
import com.fauzangifari.core.data.repository.AnimeRepositoryImpl
import com.fauzangifari.domain.repository.AnimeFavoriteRepository
import com.fauzangifari.domain.repository.AnimeRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<AnimeRepository> {
        AnimeRepositoryImpl(apiService = get())
    }

    single<AnimeFavoriteRepository> {
        AnimeFavoriteRepositoryImpl(animeFavoriteDao = get())
    }
}
