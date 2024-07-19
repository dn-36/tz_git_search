package com.project.tzgamblingcompany.presentation.ui.deteilrepo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.tzgamblingcompany.presentation.ui.common.component.BackButton
import com.project.tzgamblingcompany.presentation.ui.common.MainActivity
import com.project.tzgamblingcompany.presentation.ui.common.component.ErrorView
import com.project.tzgamblingcompany.presentation.ui.deteilrepo.component.FileList
import com.project.tzgamblingcompany.presentation.ui.deteilrepo.model.RepoDeteil
import com.project.tzgamblingcompany.presentation.ui.deteilrepo.viewmodel.RepositoryDeteilIntent
import com.project.tzgamblingcompany.presentation.ui.deteilrepo.viewmodel.RepositoryDeteilUiState
import com.project.tzgamblingcompany.presentation.ui.deteilrepo.viewmodel.RepositoryDeteilViewModel
import org.koin.androidx.compose.getViewModel

//var isUsed = true

@Preview
@Composable
fun PreviewRepositoryScreen() {
    RepositoryDeteilScreen(
        RepoDeteil("", "", ""),
        navController = NavController(MainActivity())
    )
}

@Composable
fun RepositoryDeteilScreen(
    repoInfo: RepoDeteil,
    viewModel: RepositoryDeteilViewModel = getViewModel(),
    navController: NavController,
) {

    val uiState by viewModel.uiState.collectAsState()
    viewModel.proccesIntent(
        RepositoryDeteilIntent.SetScreen(
            repoInfo
        )
    )


    Box(modifier = Modifier.padding(15.dp)) {

        when (uiState) {
            is RepositoryDeteilUiState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )

            is RepositoryDeteilUiState.Success -> {
                BackButton() { viewModel.proccesIntent(RepositoryDeteilIntent.GoBack(navController)) }

                FileList(
                    contents = (uiState as RepositoryDeteilUiState.Success).contents,
                    onFolderClick = { path ->
                        viewModel.proccesIntent(
                            RepositoryDeteilIntent.ShowPatch(
                                RepoDeteil(
                                    owner = repoInfo.owner,
                                    repo = repoInfo.repo,
                                    path = path
                                )
                            )
                        )
                    },
                    onFileClick = { url ->
                        viewModel.proccesIntent(
                            RepositoryDeteilIntent.OpenWebView(url, navController)
                        )
                    }
                )
            }

            is RepositoryDeteilUiState.Error -> {
                BackButton() { viewModel.proccesIntent(RepositoryDeteilIntent.GoBack(navController)) }
                ErrorView(message = "Error: ${(uiState as RepositoryDeteilUiState.Error).message}") {
                    viewModel.proccesIntent(RepositoryDeteilIntent.ReteryLoad)
                }

            }


        }
    }
}



