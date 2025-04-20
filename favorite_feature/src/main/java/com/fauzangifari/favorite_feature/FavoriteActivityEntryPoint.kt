package com.fauzangifari.favorite_feature

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteActivityEntryPoint {

    fun inject(favoriteActivity: FavoriteActivity)

}
