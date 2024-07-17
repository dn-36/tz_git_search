package com.project.tzgamblingcompany.data.repository

import com.project.tzgamblingcompany.data.network.GitHubApi
import org.koin.dsl.module

class GitHubRepository(private val api: GitHubApi) {
    suspend fun searchUsers(query: String) = api.searchUsers(query)
    suspend fun searchRepositories(query: String) = api.searchRepositories(query)
}

// RepositoryModule.kt
val repositoryModule = module {
    single { GitHubRepository(get()) }
}