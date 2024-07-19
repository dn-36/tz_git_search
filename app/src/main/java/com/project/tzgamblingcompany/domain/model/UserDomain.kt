package com.project.tzgamblingcompany.domain.model

data class UserDomain(
    val login: String?,
    val id: Int?,
    val avatar_url: String?,
    val html_url: String,
    val score: Float?
)