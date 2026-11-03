package com.workout_app.data.local.monthly

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monthly_plans")
data class MonthlyPlanEntity(
    @PrimaryKey(autoGenerate = true)
    val planId: Int = 0,
    val month: Int,
    val name: String,
    val description: String = "",
    val goals: String = ""
)
