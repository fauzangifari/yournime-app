package com.fauzangifari.favorite_feature.di

import com.fauzangifari.favorite_feature.ui.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteFeatureModule = module {

    viewModel {
        FavoriteViewModel(get())
    }
}