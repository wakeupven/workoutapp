package com.workout_app.data.model

import com.workout_app.data.model.difficulty.ExerciseDifficulty

data class Workout(
    val id: String,
    val name: String,
    val description: String = "",
    val exercises: List<Exercise> = emptyList(),
    val order: List<Int> = emptyList(),
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val targetMuscles: List<String> = emptyList(),
    val difficulty: ExerciseDifficulty = ExerciseDifficulty.MEDIUM
)
