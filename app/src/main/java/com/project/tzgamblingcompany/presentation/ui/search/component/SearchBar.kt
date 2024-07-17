package com.project.tzgamblingcompany.presentation.ui.search.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    TextField(
        value = query,
        onValueChange = { query = it },
        label = { Text("Search") },
        trailingIcon = {
            if (query.length >= 3) {
                IconButton(onClick = { onSearch(query) }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        }
    )
}