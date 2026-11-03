package com.workout_app.data.remote

import com.workout_app.data.api.GitHubApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepositoryProvider @Inject constructor(
    @ApplicationContext private val context: android.content.Context
) {
    fun create(): GitHubRepository {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        return GitHubRepository(retrofit.create(GitHubApi::class.java))
    }
}
