package com.workout_app.data.model

import com.workout_app.data.model.difficulty.ExerciseDifficulty

data class Exercise(
    val id: String,
    val name: String,
    val description: String = "",
    val difficulty: ExerciseDifficulty = ExerciseDifficulty.MEDIUM,
    val targetMuscles: List<String> = emptyList(),
    val equipment: List<String> = emptyList(),
    val sets: Int = 3,
    val reps: Int = 10,
    val restTime: Int = 60,
    val imageUrls: List<String> = emptyList(),
    val videoUrl: String? = null
)
