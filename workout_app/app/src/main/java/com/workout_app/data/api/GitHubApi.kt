package com.workout_app.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("repos/{repo}/contents/{path}")
    suspend fun getRepoContent(
        @Path("repo") repo: String,
        @Path("path") path: String
    ): Response<GitHubFile>

    @GET("repos/{repo}/contents/{path}")
    suspend fun getRepoContentTree(
        @Path("repo") repo: String,
        @Path("path") path: String
    ): Response<List<GitHubFile>>

    @GET("repos/{repo}/contents/{path}")
    suspend fun getExercises(
        @Path("repo") repo: String,
        @Path("path") path: String
    ): Response<List<com.workout_app.data.model.Exercise>>
}

data class GitHubFile(
    val name: String,
    val type: String,
    val path: String,
    val downloadUrl: String?
)
