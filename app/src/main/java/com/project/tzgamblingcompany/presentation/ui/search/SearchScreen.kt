package com.project.tzgamblingcompany.presentation.ui.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.tzgamblingcompany.presentation.ui.common.component.ErrorView
import com.project.tzgamblingcompany.presentation.ui.search.component.ResultList
import com.project.tzgamblingcompany.presentation.ui.search.component.SearchBar
import com.project.tzgamblingcompany.presentation.ui.search.viewmodel.SearchIntent
import com.project.tzgamblingcompany.presentation.ui.search.viewmodel.SearchViewState
import org.koin.androidx.compose.getViewModel


@Composable
fun SearchScreen(viewModel: SearchViewModel = getViewModel(), navController: NavController) {

    val state by viewModel.state.collectAsState()
    Column {
        SearchBar(state = state,
            onSearch = { query -> viewModel.processIntent(SearchIntent.SearchInfo(query)) })

        when (state) {
            is SearchViewState.Empty -> {}
            is SearchViewState.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .padding(50.dp)
                    .align(Alignment.CenterHorizontally)
            )

            is SearchViewState.Success -> ResultList(
                (state as SearchViewState.Success).results,//,{},{}
                { url ->

                    viewModel.processIntent(SearchIntent.ShowWebViewProfile(url))
                },
                { repo ->
                    viewModel.processIntent(
                        SearchIntent.ShowRepoDeteil(
                            repo.name ?: "",
                            repo.html_url ?: "",
                            navController
                        )
                    )
                }
            )

            is SearchViewState.Error -> ErrorView((state as SearchViewState.Error).message) {
                viewModel.processIntent(SearchIntent.RetrySearchInfo)
            }
        }
    }
}



