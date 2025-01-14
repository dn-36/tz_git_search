package com.project.tzgamblingcompany.presentation.ui.search.component

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.project.tzgamblingcompany.domain.usecase.SearchResult
import com.project.tzgamblingcompany.domain.model.RepositoryDomain

@Composable
fun ResultList(
    results: List<SearchResult>,
    clickUser: (user: String) -> Unit,
    clickRepo: (repo: RepositoryDomain) -> Unit
) {
    LazyColumn {
        items(results) { result ->
            Log.d("test_0101", "")
            when (result) {
                is SearchResult.UserResult -> UserCard(result.user,
                    { clickUser(result.user.html_url) }
                )

                is SearchResult.RepoResult -> RepositoryCard(
                    result.repo,
                    { clickRepo(result.repo) }
                )
            }
        }
    }
}