package com.fauzangifari.domain.di

import com.fauzangifari.domain.usecase.api.GetAnimeById
import com.fauzangifari.domain.usecase.api.GetAnimeSearch
import com.fauzangifari.domain.usecase.api.GetAnimeUpcoming
import com.fauzangifari.domain.usecase.api.GetTopAnime
import com.fauzangifari.domain.usecase.local.InsertAnimeFavorite
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetAnimeById(get()) }
    factory { GetAnimeSearch(get()) }
    factory { GetAnimeUpcoming(get()) }
    factory { GetTopAnime(get()) }
    factory { InsertAnimeFavorite(get()) }

}