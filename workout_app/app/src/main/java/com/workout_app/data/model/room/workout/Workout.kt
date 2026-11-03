package com.workout_app.data.model.room.workout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String = "",
    val notes: String? = null,
    val targetMuscles: String = "",
    val difficulty: String = "MEDIUM",
    val createdAt: Long = System.currentTimeMillis()
)
