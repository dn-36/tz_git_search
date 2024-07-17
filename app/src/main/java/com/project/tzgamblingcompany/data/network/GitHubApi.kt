package com.project.tzgamblingcompany.data.network

import com.project.tzgamblingcompany.data.model.Repository
import com.project.tzgamblingcompany.data.model.User
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// GitHubApi.kt
interface GitHubApi {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): SearchResponse<User>

    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): SearchResponse<Repository>
}

// SearchResponse.kt
data class SearchResponse<T>(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<T>
)

// NetworkModule.kt
val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }
}