package com.workout_app.data.model.room.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weekly_schedules")
data class WeeklySchedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    val exerciseIds: String
)
