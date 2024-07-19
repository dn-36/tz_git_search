package com.project.tzgamblingcompany.presentation.ui.splash.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.project.tzgamblingcompany.presentation.ui.common.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

class SplashViewModel : ViewModel() {
    fun proccesIntent(splashIntent: SplashIntent) {
        when (splashIntent) {
            is SplashIntent.SetScreen -> startLoading(splashIntent.navController)
        }
    }

    private fun startLoading(navController: NavController) {
        viewModelScope.launch {
            delay(3000) // 5 секунд задержки
            navController.navigate(Screen.Search.route)
        }
    }
}

