package com.project.tzgamblingcompany.presentation.ui.splash.viewmodel

import androidx.navigation.NavController

sealed class SplashIntent {
    class SetScreen(val navController: NavController) : SplashIntent()
}