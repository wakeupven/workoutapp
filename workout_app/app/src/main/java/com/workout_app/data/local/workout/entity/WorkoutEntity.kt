package com.workout_app.data.local.workout.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    val workoutId: Int = 0,
    val name: String,
    val description: String = "",
    val notes: String? = null,
    val targetMuscles: String = "",
    val difficulty: String = "MEDIUM",
    val createdAt: Long = System.currentTimeMillis()
)
