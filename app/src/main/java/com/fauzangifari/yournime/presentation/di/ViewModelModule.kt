package com.fauzangifari.yournime.presentation.di

import com.fauzangifari.yournime.presentation.detail.DetailViewModel
import com.fauzangifari.yournime.presentation.home.HomeViewModel
import com.fauzangifari.yournime.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        DetailViewModel(get(), get(), get())
    }

    viewModel {
        HomeViewModel(get(), get(), get())
    }

    viewModel {
        SearchViewModel(get())
    }

}