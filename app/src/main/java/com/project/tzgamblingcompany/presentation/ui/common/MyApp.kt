package com.project.tzgamblingcompany.presentation.ui.common

import android.app.Application

import com.project.tzgamblingcompany.di.appModule
import com.project.tzgamblingcompany.di.networkModule
import com.project.tzgamblingcompany.di.repositoryModule
import com.project.tzgamblingcompany.di.viewModelModule
import com.project.tzgamblingcompany.domain.usecase.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

// App.kt
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(networkModule, appModule ,
                repositoryModule, useCaseModule, viewModelModule))
        }
    }
}