package com.project.tzgamblingcompany.presentation.ui.webview.viewmodel

import androidx.compose.runtime.Composable

sealed class WebViewIntent {
    data class SetScreen(val url: String) : WebViewIntent()
}