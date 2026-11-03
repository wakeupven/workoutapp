package com.workout_app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String = "",
    val difficulty: String = "MEDIUM",
    val targetMuscles: String = "",
    val equipment: String = "",
    val sets: Int = 3,
    val reps: Int = 10,
    val restTime: Int = 60,
    val mediaType: String = "IMAGE",
    val mediaUrl: String = "",
    val videoUrl: String? = null,
    val imageUrls: String = "",
    val tags: String = ""
)
