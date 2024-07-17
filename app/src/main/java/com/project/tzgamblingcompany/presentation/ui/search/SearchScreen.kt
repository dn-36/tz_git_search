package com.project.tzgamblingcompany.presentation.ui.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.project.tzgamblingcompany.presentation.ui.search.component.ErrorView
import com.project.tzgamblingcompany.presentation.ui.search.component.ResultList
import com.project.tzgamblingcompany.presentation.ui.search.component.SearchBar
import org.koin.androidx.compose.getViewModel


@Composable
fun SearchScreen(viewModel: SearchViewModel = getViewModel()) {
    val state by viewModel.state.collectAsState()
    Log.d("MainScreen", "Current state: $state")
    Column {
        SearchBar { query -> viewModel.processIntent(SearchIntent.SearchInfo(query)) }

        when (state) {
            is SearchViewState.Loading -> CircularProgressIndicator()
            is SearchViewState.Success -> ResultList(
                (state as SearchViewState.Success).results,//,{},{}
 {url ->
     Log.d("test_0101",url)
     viewModel.processIntent(SearchIntent.ShowWebViewProfile(url))},
  {repo ->  }
            )
is SearchViewState.Error -> ErrorView((state as SearchViewState.Error).message) {
 viewModel.processIntent(SearchIntent.RetrySearchInfo)
}
}
}
}


