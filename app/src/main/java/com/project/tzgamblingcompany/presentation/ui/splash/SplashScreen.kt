package com.project.tzgamblingcompany.presentation.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@Composable
fun SplashScreen(navController: NavController,viewModel:SplashViewModel) {

    SplashComponent()
    if (viewModel.isLoadingFinished) {
     //   navController.navigate(Screen .route)
    }
}