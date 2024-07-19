package com.project.tzgamblingcompany.presentation.ui.search.viewmodel

import androidx.navigation.NavController

sealed class SearchIntent {
    data class SearchInfo(val query: String) : SearchIntent()
    data class ShowRepoDeteil(
        val repoName: String,
        val repoUrl: String,
        val navController: NavController
    ) : SearchIntent()

    object RetrySearchInfo : SearchIntent()
    data class ShowWebViewProfile(val url: String) : SearchIntent()
}