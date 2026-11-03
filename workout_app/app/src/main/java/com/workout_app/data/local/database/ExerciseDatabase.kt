package com.workout_app.data.local.database

import android.content.Context
import androidx.room.*
import com.workout_app.data.local.Exercise
import com.workout_app.data.local.Workout
import com.workout_app.data.local.progress.ProgressEntity
import com.workout_app.data.local.schedule.WeeklyScheduleEntity
import com.workout_app.data.model.room.Exercise as RoomExercise
import com.workout_app.data.model.room.Workout as RoomWorkout
import com.workout_app.data.model.room.monthly.MonthlyPlan
import com.workout_app.data.model.room.schedule.WeeklySchedule

@Database(
    entities = [
        Exercise::class,
        Workout::class,
        ProgressEntity::class,
        WeeklyScheduleEntity::class,
        MonthlyPlan::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class ExerciseDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun progressDao(): ProgressDao
    abstract fun monthlyPlanDao(): MonthlyPlanDao

    companion object {
        @Volatile
        private var INSTANCE: ExerciseDatabase? = null

        fun getInstance(context: Context): ExerciseDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseDatabase::class.java,
                    "workout_database"
                ).build()
            }
        }
    }
}

@Dao
abstract class ExerciseDao {
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

    // Flow-based operations
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

@Dao
abstract class WorkoutDao {
    // Synchronous operations
    @Query("SELECT * FROM workouts ORDER BY name")
    abstract fun getAllWorkouts(): List<Workout>

    @Query("SELECT * FROM workouts WHERE workout_id = :workoutId")
    abstract fun getWorkoutById(workoutId: String): Workout?

    @Query("SELECT * FROM workouts WHERE target_muscles LIKE '%' || :muscle || '%'")
    abstract fun getWorkoutsByTargetMuscle(muscle: String): List<Workout>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWorkout(workout: Workout)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWorkouts(workouts: List<Workout>)

    @Update
    abstract fun updateWorkout(workout: Workout)

    @Delete
    abstract fun deleteWorkout(workout: Workout)

    // Flow-based operations
    @Query("SELECT * FROM workouts")
    abstract fun getAllWorkoutsAsFlow(): Flow<List<Workout>>

    @Query("SELECT * FROM workouts WHERE workout_id = :workoutId")
    abstract fun getWorkoutByIdAsFlow(workoutId: String): Flow<Workout?>

    @Query("SELECT * FROM workouts WHERE target_muscles LIKE '%' || :muscle || '%'")
    abstract fun getWorkoutsByTargetMuscleAsFlow(muscle: String): Flow<List<Workout>>

    @Query("DELETE FROM workouts")
    abstract fun deleteAllWorkouts(): Int
}

@Dao
abstract class ScheduleDao {
    // Synchronous operations
    @Query("SELECT * FROM weekly_schedules ORDER BY day")
    abstract fun getAllSchedules(): List<WeeklyScheduleEntity>

    @Query("SELECT * FROM weekly_schedules WHERE day = :day")
    abstract fun getScheduleByDay(day: String): List<WeeklyScheduleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSchedule(schedule: WeeklyScheduleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSchedules(schedules: List<WeeklyScheduleEntity>)

    @Update
    abstract fun updateSchedule(schedule: WeeklyScheduleEntity)

    @Delete
    abstract fun deleteSchedule(schedule: WeeklyScheduleEntity)

    // Flow-based operations
    @Query("SELECT * FROM weekly_schedules")
    abstract fun getAllSchedulesAsFlow(): Flow<List<WeeklyScheduleEntity>>

    @Query("SELECT * FROM weekly_schedules WHERE day = :day")
    abstract fun getScheduleByDayAsFlow(day: String): Flow<List<WeeklyScheduleEntity>>

    @Query("DELETE FROM weekly_schedules")
    abstract fun deleteAllSchedules(): Int
}

@Dao
abstract class ProgressDao {
    // Synchronous operations
    @Query("SELECT * FROM progress ORDER BY date DESC")
    abstract fun getAllProgress(): List<ProgressEntity>

    @Query("SELECT * FROM progress WHERE workout_id = :workoutId ORDER BY date DESC")
    abstract fun getCompletedProgress(workoutId: String): List<ProgressEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProgress(progress: ProgressEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProgresses(progresses: List<ProgressEntity>)

    @Delete
    abstract fun deleteProgress(progress: ProgressEntity)

    // Flow-based operations
    @Query("SELECT * FROM progress")
    abstract fun getAllProgressAsFlow(): Flow<List<ProgressEntity>>

    @Query("SELECT * FROM progress WHERE workout_id = :workoutId ORDER BY date DESC")
    abstract fun getCompletedProgressAsFlow(workoutId: String): Flow<List<ProgressEntity>>

    @Query("DELETE FROM progress")
    abstract fun deleteAllProgress(): Int
}

@Dao
abstract class MonthlyPlanDao {
    // Synchronous operations
    @Query("SELECT * FROM monthly_plans ORDER BY month")
    abstract fun getAllPlans(): List<MonthlyPlan>

    @Query("SELECT * FROM monthly_plans WHERE month = :month")
    abstract fun getPlanByMonth(month: Int): MonthlyPlan?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPlan(plan: MonthlyPlan)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPlans(plans: List<MonthlyPlan>)

    @Update
    abstract fun updatePlan(plan: MonthlyPlan)

    @Delete
    abstract fun deletePlan(plan: MonthlyPlan)

    // Flow-based operations
    @Query("SELECT * FROM monthly_plans")
    abstract fun getAllPlansAsFlow(): Flow<List<MonthlyPlan>>

    @Query("SELECT * FROM monthly_plans WHERE month = :month")
    abstract fun getPlanByMonthAsFlow(month: Int): Flow<MonthlyPlan?>

    @Query("DELETE FROM monthly_plans")
    abstract fun deleteAllPlans(): Int
}
