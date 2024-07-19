package com.project.tzgamblingcompany.presentation.ui.webview.viewmodel

import android.util.Log
import androidx.compose.material3.Text
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent


class WebViewViewModel : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(WebViewState(false, ""))
    val uiState = _uiState

    fun proccesIntent(intent: WebViewIntent) {
        when (intent) {
            is WebViewIntent.SetScreen -> setScreen(
                intent.url
            )
        }

    }

    private fun setScreen(url: String) {
        if (url.isNotBlank()) {
            _uiState.value = WebViewState(true, replaceAmpersandWithSlash(url))
        } else {
            _uiState.value = WebViewState(false, "")
        }
    }

    private fun replaceAmpersandWithSlash(input: String): String {
        return input.replace("&", "/")
    }
}