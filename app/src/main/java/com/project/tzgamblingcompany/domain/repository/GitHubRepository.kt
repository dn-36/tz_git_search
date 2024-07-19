package com.project.tzgamblingcompany.domain.repository

import com.project.tzgamblingcompany.data.network.GitHubApi
import org.koin.dsl.module

class GitHubRepository(private val api: GitHubApi) {
    suspend fun searchUsers(query: String) = api.searchUsers(query)
    suspend fun searchRepositories(query: String) = api.searchRepositories(query)
    suspend fun getRepositoryContents(owner: String, repo: String, path: String) =
        api.getRepositoryContents(owner, repo, path)
}

