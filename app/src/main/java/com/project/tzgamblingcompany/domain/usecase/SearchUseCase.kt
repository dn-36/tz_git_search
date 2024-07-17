package com.project.tzgamblingcompany.domain.usecase

import android.util.Log
import com.project.tzgamblingcompany.data.model.Repository
import com.project.tzgamblingcompany.data.model.User
import com.project.tzgamblingcompany.data.repository.GitHubRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.koin.dsl.module

// SearchUseCase.kt
class SearchUseCase(private val repository: GitHubRepository) {
    suspend fun search(query: String): List<SearchResult> = coroutineScope {
        val usersDeferred = async { repository.searchUsers(query) }
        val reposDeferred = async { repository.searchRepositories(query) }

        val users = usersDeferred.await().items
        val repos = reposDeferred.await().items
        (users.map { SearchResult.UserResult(it) } + repos.map { SearchResult.RepoResult(it) })
            .sortedBy { it.displayName }
    }
}

// SearchResult.kt
sealed class SearchResult(val displayName: String) {
    data class UserResult(val user: User) : SearchResult(user.login?:"")
    data class RepoResult(val repo: Repository) : SearchResult(repo.name?:"")
}

// UseCaseModule.kt
val useCaseModule = module {
    single { SearchUseCase(get()) }
}