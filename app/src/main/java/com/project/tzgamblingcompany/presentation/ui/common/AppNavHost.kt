package com.project.tzgamblingcompany.presentation.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.tzgamblingcompany.presentation.ui.search.SearchScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "main") {
        composable("main") { SearchScreen(viewModel = getViewModel()) }
        composable("details/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            DetailsScreen(url)
        }
    }
}

@Composable
fun DetailsScreen(url: String?) {
    if (url != null) {
        WebView(url = url)
    } else {
        Text("Invalid URL")
    }
}

@Composable
fun WebView(url: String) {
    AndroidView(
        factory = { context ->
            android.webkit.WebView(context).apply {
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        },
        update = { it.loadUrl(url) }
    )
}