package com.project.tzgamblingcompany.presentation.ui.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {


    var isLoadingFinished by mutableStateOf(false)
        private set

    init {
        startLoading()
    }

    private fun startLoading() {
        viewModelScope.launch {
            delay(3000) // 5 секунд задержки
            isLoadingFinished = true
        }
    }
}