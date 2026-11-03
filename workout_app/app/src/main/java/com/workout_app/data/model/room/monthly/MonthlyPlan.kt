package com.workout_app.data.model.room.monthly

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monthly_plans")
data class MonthlyPlan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val month: Int,
    val name: String,
    val description: String = "",
    val goals: String = ""
)
