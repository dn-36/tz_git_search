package com.project.tzgamblingcompany.presentation.ui.search

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.project.tzgamblingcompany.domain.usecase.SearchUseCase
import com.project.tzgamblingcompany.presentation.ui.common.MainActivity
import com.project.tzgamblingcompany.presentation.ui.common.navigation.Screen
import com.project.tzgamblingcompany.presentation.ui.search.viewmodel.SearchIntent
import com.project.tzgamblingcompany.presentation.ui.search.viewmodel.SearchViewState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {
    private var savedQery = ""
    private val _state = MutableStateFlow<SearchViewState>(SearchViewState.Empty)
    val state: StateFlow<SearchViewState> get() = _state.asStateFlow()

    fun processIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.SearchInfo -> search(intent.query)
            is SearchIntent.RetrySearchInfo -> retry(savedQery)
            is SearchIntent.ShowWebViewProfile -> openUrlInBrowser(intent.url)
            is SearchIntent.ShowRepoDeteil -> showRepoDeteil(
                intent.navController, intent.repoName, intent.repoUrl
            )

        }
    }

    private fun showRepoDeteil(navController: NavController, repoName: String, repoUrl: String) {
        navController.navigate(
            "${Screen.DeteilRepo.route}/${repoName}/" +
                    "${extractUsername(repoUrl)}"
        )
    }

    private fun search(query: String) {
        savedQery = query
        viewModelScope.launch {
            _state.value = SearchViewState.Loading
            try {
                val results = searchUseCase.search(query)
                _state.value = SearchViewState.Success(results)
            } catch (e: Exception) {
                _state.value = SearchViewState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun retry(query: String) {
        search(query)
    }
}

private fun extractUsername(url: String): String {
    val prefix = "https://github.com/"
    if (!url.startsWith(prefix)) {
        return ""
    }

    val withoutPrefix = url.removePrefix(prefix)

    val username = withoutPrefix.substringBefore('/')
    return username
}

private fun openUrlInBrowser(url: String) {

    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }
    val chooser = Intent.createChooser(intent, "Выберите браузер")
    MainActivity.context.startActivity(chooser)
}
