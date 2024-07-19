package com.project.tzgamblingcompany.presentation.ui.deteilrepo.viewmodel

import com.project.tzgamblingcompany.domain.model.RepoContentDomain


sealed class RepositoryDeteilUiState {
    object Loading : RepositoryDeteilUiState()
    data class Success(val contents: List<RepoContentDomain>) : RepositoryDeteilUiState()
    data class Error(val message: String) : RepositoryDeteilUiState()
}