package com.workout_app.data.remote

import com.workout_app.data.api.GitHubApi
import com.workout_app.data.model.GitHubFile

class GitHubRepository(private val api: GitHubApi) {
    fun getRepoContent(repo: String, path: String): String? {
        return try {
            val response = api.getRepoContent(repo, path)
            if (response.isSuccessful && response.body() != null) {
                response.body()?.downloadUrl
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    fun getExercisesFromRepo(repo: String): List<com.workout_app.data.model.Exercise>? {
        return try {
            val response = api.getExercises(repo)
            if (response.isSuccessful && response.body() != null) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}

object GitHubRepositoryProvider {
    fun create(): GitHubRepository {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        return GitHubRepository(retrofit.create(GitHubApi::class.java))
    }
}
