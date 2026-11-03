package com.workout_app.data.local.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weekly_schedules")
data class WeeklyScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val scheduleId: Int = 0,
    val day: String,
    val exerciseIds: String
)
