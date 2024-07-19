package com.project.tzgamblingcompany.presentation.ui.deteilrepo.viewmodel

import androidx.navigation.NavController
import com.project.tzgamblingcompany.presentation.ui.deteilrepo.model.RepoDeteil

sealed class RepositoryDeteilIntent {
    data class SetScreen(
        val repoDeteil: RepoDeteil
    ) : RepositoryDeteilIntent()

    data class ShowPatch(val repoDeteil: RepoDeteil) : RepositoryDeteilIntent()
    data class GoBack(
        val navController: NavController
    ) : RepositoryDeteilIntent()

    data class OpenWebView(
        val url: String,
        val navController: NavController
    ) : RepositoryDeteilIntent()

    object ReteryLoad : RepositoryDeteilIntent()

}