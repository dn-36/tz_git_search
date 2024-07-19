package com.project.tzgamblingcompany.di

import com.project.tzgamblingcompany.data.network.GitHubApi
import com.project.tzgamblingcompany.domain.repository.GitHubRepository
import com.project.tzgamblingcompany.presentation.ui.deteilrepo.viewmodel.RepositoryDeteilViewModel
import com.project.tzgamblingcompany.presentation.ui.search.SearchViewModel
import com.project.tzgamblingcompany.presentation.ui.splash.viewmodel.SplashViewModel
import com.project.tzgamblingcompany.presentation.ui.webview.viewmodel.WebViewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    single { GitHubRepository(get()) }
    viewModel { RepositoryDeteilViewModel() }
}

val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { SplashViewModel() }
    viewModel { WebViewViewModel() }

}

val repositoryModule = module {
    single { GitHubRepository(get()) }
}
val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }
}