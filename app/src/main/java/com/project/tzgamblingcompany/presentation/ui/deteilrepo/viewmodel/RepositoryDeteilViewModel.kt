package com.project.tzgamblingcompany.presentation.ui.deteilrepo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.tzgamblingcompany.presentation.ui.common.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


import android.util.Log
import androidx.navigation.NavController
import com.project.tzgamblingcompany.domain.model.RepoContentDomain
import com.project.tzgamblingcompany.domain.repository.GitHubRepository
import com.project.tzgamblingcompany.presentation.ui.deteilrepo.model.RepoDeteil


class RepositoryDeteilViewModel : ViewModel(), KoinComponent {
    private val repository: GitHubRepository by inject()

    private val _uiState =
        MutableStateFlow<RepositoryDeteilUiState>(RepositoryDeteilUiState.Loading)
    val uiState: StateFlow<RepositoryDeteilUiState> = _uiState
    private var isUsedSetScreen = true
    private var clickedPatch = RepoDeteil("", "", "")
    fun proccesIntent(intent: RepositoryDeteilIntent) {
        when (intent) {
            is RepositoryDeteilIntent.SetScreen -> {
                setScreen(intent.repoDeteil)
            }

            is RepositoryDeteilIntent.GoBack -> {
                goBack(intent.navController)
            }

            is RepositoryDeteilIntent.ShowPatch -> {
                showPatch(intent.repoDeteil)
            }

            is RepositoryDeteilIntent.OpenWebView -> {
                intent.navController.navigate(
                    "${Screen.WebView.route}/${
                        replaceSlashWithAmpersand(
                            intent.url
                        )
                    }"
                )
            }

            is RepositoryDeteilIntent.ReteryLoad -> {
                loadContents(clickedPatch)
            }
        }
    }

    private fun loadContents(repoDeteil: RepoDeteil) {
        viewModelScope.launch {
            _uiState.value = RepositoryDeteilUiState.Loading
            try {
                val contents = repository.getRepositoryContents(
                    repoDeteil.owner,
                    repoDeteil.repo,
                    repoDeteil.path
                )
                _uiState.value = RepositoryDeteilUiState.Success(
                    contents.map { RepoContentDomain(it.name, it.path, it.type, it.download_url) }

                )
            } catch (e: Exception) {
                _uiState.value = RepositoryDeteilUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    private fun setScreen(repoDeteil: RepoDeteil) {
        if (isUsedSetScreen) {
            loadContents(repoDeteil)
            isUsedSetScreen = false
        }
    }

    private fun showPatch(repoDeteil: RepoDeteil) {
        clickedPatch = repoDeteil
        loadContents(repoDeteil)
    }

    private fun replaceSlashWithAmpersand(input: String): String {
        return input.replace("/", "&")
    }

    private fun goBack(navController: NavController) {
        if (clickedPatch.path.isNotBlank()) {
            if (clickedPatch.path.contains('/')) {
                val actualPatch = clickedPatch.path.substringBeforeLast('/')
                val actualInfoRepo = RepoDeteil(clickedPatch.owner, clickedPatch.repo, actualPatch)
                loadContents(actualInfoRepo)
                clickedPatch = actualInfoRepo
            } else {
                val actualInfoRepo = RepoDeteil(clickedPatch.owner, clickedPatch.repo, "")
                loadContents(actualInfoRepo)
                clickedPatch = actualInfoRepo
            }
        } else {
            navController.popBackStack()
        }

    }
}

