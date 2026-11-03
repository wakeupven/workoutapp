package com.workout_app.data.local.databinding

import androidx.room.*

@Dao
abstract class RoomDao {

    // Synchronous operations
    @Query("SELECT * FROM exercises ORDER BY name")
    abstract fun getAllExercises(): List<Exercise>

    @Query("SELECT * FROM exercises WHERE exercise_id = :exerciseId")
    abstract fun getExerciseById(exerciseId: String): Exercise?

    @Query("SELECT * FROM exercises WHERE difficulty = :difficulty")
    abstract fun getExercisesByDifficulty(difficulty: String): List<Exercise>

    @Query("SELECT * FROM exercises WHERE target_muscles LIKE '%' || :muscle || '%'")
    abstract fun getExercisesByTargetMuscle(muscle: String): List<Exercise>

    @Query("SELECT * FROM exercises WHERE equipment LIKE '%' || :equipment || '%'")
    abstract fun getExercisesByEquipment(equipment: String): List<Exercise>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertExercise(exercise: Exercise)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertExercises(exercises: List<Exercise>)

    @Update
    abstract fun updateExercise(exercise: Exercise)

    @Delete
    abstract fun deleteExercise(exercise: Exercise)

    // Flow-based operations for reactive UI
    @Query("SELECT * FROM exercises")
    abstract fun getAllExercisesAsFlow(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE exercise_id = :exerciseId")
    abstract fun getExerciseByIdAsFlow(exerciseId: String): Flow<Exercise?>

    @Query("SELECT * FROM exercises WHERE difficulty = :difficulty")
    abstract fun getExercisesByDifficultyAsFlow(difficulty: String): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE target_muscles LIKE '%' || :muscle || '%'")
    abstract fun getExercisesByTargetMuscleAsFlow(muscle: String): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE equipment LIKE '%' || :equipment || '%'")
    abstract fun getExercisesByEquipmentAsFlow(equipment: String): Flow<List<Exercise>>

    @Query("DELETE FROM exercises")
    abstract fun deleteAllExercises(): Int
}
