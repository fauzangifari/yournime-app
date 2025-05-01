package com.fauzangifari.domain.di

import com.fauzangifari.domain.usecase.api.GetAnimeById
import com.fauzangifari.domain.usecase.api.GetAnimeSearch
import com.fauzangifari.domain.usecase.api.GetAnimeUpcoming
import com.fauzangifari.domain.usecase.api.GetTopAnime
import com.fauzangifari.domain.usecase.local.DeleteAnimeFavorite
import com.fauzangifari.domain.usecase.local.GetAllAnimeFavorite
import com.fauzangifari.domain.usecase.local.GetIsAnimeFavorite
import com.fauzangifari.domain.usecase.local.InsertAnimeFavorite
import org.koin.dsl.module

val useCaseModule = module {

    // API
    factory { GetAnimeById(get()) }
    factory { GetAnimeSearch(get()) }
    factory { GetAnimeUpcoming(get()) }
    factory { GetTopAnime(get()) }

    // Local
    factory { InsertAnimeFavorite(get()) }
    factory { GetAllAnimeFavorite(get()) }
    factory { GetIsAnimeFavorite(get()) }
    factory { DeleteAnimeFavorite(get()) }
}