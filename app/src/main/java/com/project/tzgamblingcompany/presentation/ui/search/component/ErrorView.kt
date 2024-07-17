package com.project.tzgamblingcompany.presentation.ui.search.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Column {
        Text(message, color = Color.Red)
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}