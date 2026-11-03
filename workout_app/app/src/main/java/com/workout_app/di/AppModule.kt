package com.workout_app.di

import android.content.Context
import androidx.room.Room
import com.workout_app.data.local.database.ExerciseDatabase
import com.workout_app.data.local.repository.ExerciseRepository
import com.workout_app.data.local.repository.WorkoutRepository
import com.workout_app.data.local.database.ExerciseDao
import com.workout_app.data.local.database.WorkoutDao
import com.workout_app.data.local.database.ScheduleDao
import com.workout_app.data.local.database.ProgressDao
import com.workout_app.data.local.database.MonthlyPlanDao
import com.workout_app.data.remote.GitHubRepository
import com.workout_app.data.remote.GitHubRepositoryProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ExerciseDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ExerciseDatabase::class.java,
            "workout_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideExerciseDao(database: ExerciseDatabase): ExerciseDao {
        return database.exerciseDao()
    }

    @Provides
    @Singleton
    fun provideWorkoutDao(database: ExerciseDatabase): WorkoutDao {
        return database.workoutDao()
    }

    @Provides
    @Singleton
    fun provideScheduleDao(database: ExerciseDatabase): ScheduleDao {
        return database.scheduleDao()
    }

    @Provides
    @Singleton
    fun provideProgressDao(database: ExerciseDatabase): ProgressDao {
        return database.progressDao()
    }

    @Provides
    @Singleton
    fun provideMonthlyPlanDao(database: ExerciseDatabase): MonthlyPlanDao {
        return database.monthlyPlanDao()
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(exerciseDao: ExerciseDao): ExerciseRepository {
        return ExerciseRepository(exerciseDao)
    }

    @Provides
    @Singleton
    fun provideWorkoutRepository(
        workoutDao: WorkoutDao,
        scheduleDao: ScheduleDao,
        progressDao: ProgressDao,
        monthlyPlanDao: MonthlyPlanDao
    ): WorkoutRepository {
        return WorkoutRepository(workoutDao, scheduleDao, progressDao, monthlyPlanDao)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubApi(retrofit: Retrofit): GitHubApi {
        return retrofit.create(GitHubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGitHubRepository(
        @ApplicationContext context: Context
    ): GitHubRepository {
        val repositoryProvider = GitHubRepositoryProvider(context)
        return repositoryProvider.create()
    }
}
