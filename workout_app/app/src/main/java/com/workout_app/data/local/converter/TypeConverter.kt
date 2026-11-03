package com.workout_app.data.local.converter

import androidx.room.TypeConverter
import com.workout_app.data.model.ExerciseDifficulty
import com.workout_app.data.model.MediaType

class TypeConverter {
    @TypeConverter
    fun fromExerciseDifficulty(value: ExerciseDifficulty?): String? {
        return value?.name
    }

    @TypeConverter
    fun toExerciseDifficulty(value: String?): ExerciseDifficulty? {
        return ExerciseDifficulty.valueOf(value?.uppercase() ?: "MEDIUM")
    }

    @TypeConverter
    fun fromMediaType(value: MediaType?): String? {
        return value?.name
    }

    @TypeConverter
    fun toMediaType(value: String?): MediaType? {
        return MediaType.valueOf(value?.uppercase() ?: "IMAGE")
    }
}
