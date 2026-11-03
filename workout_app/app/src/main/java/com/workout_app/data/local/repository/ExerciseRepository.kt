package com.workout_app.data.local.repository

import androidx.lifecycle.LiveData
import com.workout_app.data.local.Exercise
import com.workout_app.data.local.database.ExerciseDao
import kotlinx.coroutines.flow.Flow

class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    // Сynchronous operations for immediate use
    fun getAllExercises(): List<Exercise> {
        return exerciseDao.getAllExercises()
    }

    fun getExerciseById(exerciseId: Int): Exercise? {
        return exerciseDao.getExerciseById(exerciseId)
    }

    fun getExercisesByDifficulty(difficulty: String): List<Exercise> {
        return exerciseDao.getExercisesByDifficulty(difficulty)
    }

    fun getExercisesByTargetMuscle(muscle: String): List<Exercise> {
        return exerciseDao.getExercisesByTargetMuscle(muscle)
    }

    fun getExercisesByEquipment(equipment: String): List<Exercise> {
        return exerciseDao.getExercisesByEquipment(equipment)
    }

    // Flow-based operations for reactive UI updates
    fun getAllExercisesAsFlow(): Flow<List<Exercise>> {
        return exerciseDao.getAllExercisesAsFlow()
    }

    fun getExerciseByIdAsFlow(exerciseId: Int): Flow<Exercise?> {
        return exerciseDao.getExerciseByIdAsFlow(exerciseId)
    }

    // CRUD operations
    fun insertExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise)
    }

    fun insertExercises(exercises: List<Exercise>) {
        exerciseDao.insertExercises(exercises)
    }

    fun updateExercise(exercise: Exercise) {
        exerciseDao.updateExercise(exercise)
    }

    fun deleteExercise(exercise: Exercise) {
        exerciseDao.deleteExercise(exercise)
    }

    fun deleteAllExercises() {
        exerciseDao.deleteAllExercises()
    }
}
