package com.workout_app.data.model.identifier

import androidx.room.TypeConverter

class ExerciseIdConverter {
    @TypeConverter
    fun fromString(value: String?): Int? {
        return value?.toIntOrNull()
    }

    @TypeConverter
    fun toString(value: Int?): String? {
        return value?.toString()
    }
}
