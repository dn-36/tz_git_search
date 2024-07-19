package com.project.tzgamblingcompany.presentation.ui.search.viewmodel

import com.project.tzgamblingcompany.domain.usecase.SearchResult

sealed class SearchViewState {
    object Empty : SearchViewState()
    object Loading : SearchViewState()
    data class Success(val results: List<SearchResult>) : SearchViewState()
    data class Error(val message: String) : SearchViewState()
}