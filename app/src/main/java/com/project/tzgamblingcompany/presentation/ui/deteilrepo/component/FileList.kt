package com.project.tzgamblingcompany.presentation.ui.deteilrepo.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.tzgamblingcompany.R
import com.project.tzgamblingcompany.domain.model.RepoContentDomain


@Preview
@Composable
fun PreviewFileList() {
    FileList(
        listOf(RepoContentDomain("jdncds", "dsjnnc", "sdijjds", "dskj")),
        {}, {})
}

@Composable
fun FileList(
    contents: List<RepoContentDomain>,
    onFolderClick: (String) -> Unit,
    onFileClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier

            .padding(top = 100.dp)
    ) {
        items(contents.size) { index ->
            ListItem(
                modifier = Modifier.clickable {
                    if (contents[index].type == "dir") onFolderClick(
                        contents[index].path
                    )
                    else onFileClick(contents[index].download_url ?: "")
                },
                headlineContent = {
                    if (contents[index].type == "dir") {
                        RepoDeteilItem(R.drawable.ic_folder, contents[index].name)

                    } else {
                        RepoDeteilItem(R.drawable.ic_file, contents[index].name)
                    }
                })
        }
    }
}