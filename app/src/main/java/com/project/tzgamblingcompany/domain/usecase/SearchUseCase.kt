package com.project.tzgamblingcompany.domain.usecase


import com.project.tzgamblingcompany.domain.repository.GitHubRepository
import com.project.tzgamblingcompany.domain.model.RepositoryDomain
import com.project.tzgamblingcompany.domain.model.UserDomain
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.koin.dsl.module

class SearchUseCase(private val repository: GitHubRepository) {
    suspend fun search(query: String): List<SearchResult> = coroutineScope {
        val usersDeferred = async { repository.searchUsers(query) }
        val reposDeferred = async { repository.searchRepositories(query) }

        val users = usersDeferred.await().items
        val repos = reposDeferred.await().items
        (users.map {
            SearchResult.UserResult(
                UserDomain(it.login, it.id, it.avatar_url, it.html_url, it.score)
            )
        }
                + repos.map {
            SearchResult.RepoResult(
                RepositoryDomain(
                    it.name,
                    it.forks_count,
                    it.html_url,
                    it.html_url
                )
            )
        })
            .sortedBy { it.displayName }
    }
}

// SearchResult.kt
sealed class SearchResult(val displayName: String) {
    data class UserResult(val user: UserDomain) : SearchResult(user.login ?: "")
    data class RepoResult(val repo: RepositoryDomain) : SearchResult(repo.name ?: "")
}

// UseCaseModule.kt
val useCaseModule = module {
    single { SearchUseCase(get()) }
}