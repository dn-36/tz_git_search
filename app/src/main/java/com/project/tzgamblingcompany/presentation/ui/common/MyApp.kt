package com.project.tzgamblingcompany.presentation.ui.common

import android.app.Application
import com.project.tzgamblingcompany.data.network.networkModule
import com.project.tzgamblingcompany.data.repository.repositoryModule
import com.project.tzgamblingcompany.domain.usecase.useCaseModule
import com.project.tzgamblingcompany.presentation.ui.search.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

// App.kt
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(networkModule, repositoryModule, useCaseModule, viewModelModule))
        }
    }
}