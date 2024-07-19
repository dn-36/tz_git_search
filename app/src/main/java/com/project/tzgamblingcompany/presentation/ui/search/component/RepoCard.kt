package com.project.tzgamblingcompany.presentation.ui.search.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.project.tzgamblingcompany.domain.model.RepositoryDomain


@Composable
fun RepositoryCard(repository: RepositoryDomain, onRepoClick: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onRepoClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = repository.name ?: "", style = MaterialTheme.typography.titleLarge)
            Text(
                text = "Forks: ${repository.forks_count}",
                style = MaterialTheme.typography.bodyMedium
            )
            (repository.description).let {
                Text(text = it ?: "", style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "View on GitHub",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { openUrl(context, repository.html_url ?: "") }
            )
        }
    }
}

fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}