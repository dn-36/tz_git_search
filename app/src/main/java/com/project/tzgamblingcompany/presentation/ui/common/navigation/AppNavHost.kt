package com.project.tzgamblingcompany.presentation.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.tzgamblingcompany.presentation.ui.deteilrepo.model.RepoDeteil
import com.project.tzgamblingcompany.presentation.ui.deteilrepo.RepositoryDeteilScreen
import com.project.tzgamblingcompany.presentation.ui.search.SearchScreen
import com.project.tzgamblingcompany.presentation.ui.splash.SplashScreen
import com.project.tzgamblingcompany.presentation.ui.webview.WebViewScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavHost() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) { SplashScreen(navController = navController) }
        composable(Screen.Search.route) { SearchScreen(viewModel = getViewModel(), navController) }
        composable("${Screen.DeteilRepo.route}/{repo}/{owner}") { backStackEntry ->
            val repo = backStackEntry.arguments?.getString("repo")
            val owner = backStackEntry.arguments?.getString("owner")

            RepositoryDeteilScreen(
                repoInfo = RepoDeteil(repo = repo ?: "", owner = owner ?: ""),
                navController = navController,
                viewModel = getViewModel()
            )
        }
        composable("${Screen.WebView.route}/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            WebViewScreen(url)
        }
    }
}

