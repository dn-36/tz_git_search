package com.project.tzgamblingcompany.domain.model

data class RepoContentDomain(
    val name: String,
    val path: String,
    val type: String,
    val download_url: String?
)