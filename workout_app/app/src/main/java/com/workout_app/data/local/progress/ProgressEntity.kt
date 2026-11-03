package com.workout_app.data.local.progress

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "progress")
data class ProgressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val workoutId: String,
    val exerciseId: String,
    val exerciseName: String,
    val date: LocalDateTime,
    val setsCompleted: Int,
    val repsCompleted: Int,
    val weight: Double,
    val notes: String? = null
)
