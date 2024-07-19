package com.project.tzgamblingcompany.presentation.ui.search.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.tzgamblingcompany.presentation.ui.search.viewmodel.SearchViewState

@Composable
fun SearchBar(onSearch: (String) -> Unit, state: SearchViewState) {
    var query by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .padding(25.dp)
            .fillMaxWidth(),
        value = query,
        onValueChange = { query = it },
        label = { Text("Search") },
        trailingIcon = {
            if (query.length >= 3 && state != SearchViewState.Loading) {
                IconButton(onClick = { onSearch(query) }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        }
    )
}