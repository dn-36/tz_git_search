package com.project.tzgamblingcompany.presentation.ui.common

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.tzgamblingcompany.presentation.ui.search.SearchScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main") {
        composable("main") { SearchScreen() }
    }
}