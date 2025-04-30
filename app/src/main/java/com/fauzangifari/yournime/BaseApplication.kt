package com.fauzangifari.yournime

import android.app.Application
import com.fauzangifari.core.di.databaseModule
import com.fauzangifari.core.di.networkModule
import com.fauzangifari.core.di.repositoryModule
import com.fauzangifari.domain.di.useCaseModule
import com.fauzangifari.yournime.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
         startKoin {
             androidLogger()
             androidContext(this@BaseApplication)
             modules(
                 listOf(
                     databaseModule,
                     networkModule,
                     repositoryModule,
                     useCaseModule,
                     viewModelModule,
                 )
             )
         }
    }
}