package com.project.tzgamblingcompany.presentation.ui.search

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.tzgamblingcompany.domain.usecase.SearchResult
import com.project.tzgamblingcompany.domain.usecase.SearchUseCase
import com.project.tzgamblingcompany.presentation.ui.common.MainActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// SearchViewState.kt
sealed class SearchViewState {
    object Loading : SearchViewState()
    data class Success(val results: List<SearchResult>) : SearchViewState()
    data class Error(val message: String) : SearchViewState()
}

// SearchIntent.kt
sealed class SearchIntent {
    data class SearchInfo(val query: String) : SearchIntent()
    object RetrySearchInfo : SearchIntent()
    data class ShowWebViewProfile(val url:String): SearchIntent()
}

// SearchViewModel.kt
class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {
    private val _state = MutableStateFlow<SearchViewState>(SearchViewState.Loading)
    val state: StateFlow<SearchViewState> get() = _state.asStateFlow()

    fun processIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.SearchInfo -> search(intent.query)
            is SearchIntent.RetrySearchInfo -> retry()
            is SearchIntent.ShowWebViewProfile -> openUrlInBrowser(
                intent.url
            )

        }
    }

    private fun search(query: String) {
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

    private fun retry() {

    }
}

// ViewModelModule.kt
val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
}

private fun openUrlInBrowser(url: String) {

    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }
    val chooser = Intent.createChooser(intent, "Выберите браузер")
    MainActivity.context.startActivity(chooser)
}
