package com.project.tzgamblingcompany.presentation.ui.webview.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

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