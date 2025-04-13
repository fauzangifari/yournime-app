package com.fauzangifari.core.di

import com.fauzangifari.core.common.Constant
import com.fauzangifari.core.data.source.remote.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String = Constant.BASE_URL

    @Singleton
    @Provides
    fun provideRetrofitClient(
        @Named("base_url") baseUrl: String
    ) : Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ) : ApiService = retrofit.create(ApiService::class.java)
}