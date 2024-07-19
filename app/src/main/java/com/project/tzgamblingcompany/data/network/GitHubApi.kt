package com.project.tzgamblingcompany.data.network


import com.project.tzgamblingcompany.data.model.RepoContentData
import com.project.tzgamblingcompany.data.model.RepositoryData
import com.project.tzgamblingcompany.data.model.UserData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// GitHubApi.kt
interface GitHubApi {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): SearchResponse<UserData>

    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): SearchResponse<RepositoryData>

    @GET("repos/{owner}/{repo}/contents/{path}")
    suspend fun getRepositoryContents(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("path") path: String = ""
    ): List<RepoContentData>
}

data class SearchResponse<T>(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<T>
)
