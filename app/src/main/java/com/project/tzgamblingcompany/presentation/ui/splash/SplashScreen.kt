package com.project.tzgamblingcompany.presentation.ui.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.project.tzgamblingcompany.presentation.ui.splash.viewmodel.SplashIntent
import com.project.tzgamblingcompany.presentation.ui.splash.viewmodel.SplashViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = getViewModel()) {
    SplashComponent()
    viewModel.proccesIntent(SplashIntent.SetScreen(navController))

}