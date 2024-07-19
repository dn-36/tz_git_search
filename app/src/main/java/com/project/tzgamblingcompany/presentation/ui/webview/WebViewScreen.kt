package com.project.tzgamblingcompany.presentation.ui.webview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.project.tzgamblingcompany.presentation.ui.webview.component.WebView
import com.project.tzgamblingcompany.presentation.ui.webview.viewmodel.WebViewIntent
import com.project.tzgamblingcompany.presentation.ui.webview.viewmodel.WebViewViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun WebViewScreen(url: String?, viewModel: WebViewViewModel = getViewModel()) {
    viewModel.proccesIntent(WebViewIntent.SetScreen(url ?: ""))
    if (viewModel.uiState.value.isSeccuss) {
        WebView(url = viewModel.uiState.value.url)
    } else {
        Text("Invalid URL")
    }


}
