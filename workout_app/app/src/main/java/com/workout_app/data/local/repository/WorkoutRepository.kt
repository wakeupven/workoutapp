package com.workout_app.data.local.repository

import androidx.lifecycle.LiveData
import com.workout_app.data.local.Workout
import com.workout_app.data.local.progress.ProgressEntity
import com.workout_app.data.local.schedule.WeeklyScheduleEntity
import com.workout_app.data.local.database.ExerciseDao
import com.workout_app.data.local.database.WorkoutDao
import com.workout_app.data.local.database.ScheduleDao
import com.workout_app.data.local.database.ProgressDao
import com.workout_app.data.local.database.MonthlyPlanDao

class WorkoutRepository(
    private val workoutDao: WorkoutDao,
    private val scheduleDao: ScheduleDao,
    private val progressDao: ProgressDao,
    private val monthlyPlanDao: MonthlyPlanDao
) {
    fun getAllWorkouts(): List<Workout> {
        return workoutDao.getAllWorkouts()
    }

    fun getWorkoutById(workoutId: Int): Workout? {
        return workoutDao.getWorkoutById(workoutId)
    }

    fun getWorkoutsByTargetMuscle(muscle: String): List<Workout> {
        return workoutDao.getWorkoutsByTargetMuscle(muscle)
    }

    fun insertWorkout(workout: Workout) {
        workoutDao.insertWorkout(workout)
    }

    fun insertWorkouts(workouts: List<Workout>) {
        workoutDao.insertWorkouts(workouts)
    }

    fun updateWorkout(workout: Workout) {
        workoutDao.updateWorkout(workout)
    }

    fun deleteWorkout(workout: Workout) {
        workoutDao.deleteWorkout(workout)
    }

    fun getAllSchedules(): List<WeeklyScheduleEntity> {
        return scheduleDao.getAllSchedules()
    }

    fun getScheduleByDay(day: String): List<WeeklyScheduleEntity> {
        return scheduleDao.getScheduleByDay(day)
    }

    fun insertSchedule(schedule: WeeklyScheduleEntity) {
        scheduleDao.insertSchedule(schedule)
    }

    fun insertSchedules(schedules: List<WeeklyScheduleEntity>) {
        scheduleDao.insertSchedules(schedules)
    }

    fun updateSchedule(schedule: WeeklyScheduleEntity) {
        scheduleDao.updateSchedule(schedule)
    }

    fun deleteSchedule(schedule: WeeklyScheduleEntity) {
        scheduleDao.deleteSchedule(schedule)
    }

    fun getAllProgress(): List<ProgressEntity> {
        return progressDao.getAllProgress()
    }

    fun getCompletedProgress(workoutId: String): List<ProgressEntity> {
        return progressDao.getCompletedProgress(workoutId)
    }

    fun insertProgress(progress: ProgressEntity) {
        progressDao.insertProgress(progress)
    }

    fun insertProgresses(progresses: List<ProgressEntity>) {
        progressDao.insertProgresses(progresses)
    }

    fun deleteProgress(progress: ProgressEntity) {
        progressDao.deleteProgress(progress)
    }

    fun getAllMonthlyPlans(): List<MonthlyPlan> {
        return monthlyPlanDao.getAllPlans()
    }

    fun getPlanByMonth(month: Int): MonthlyPlan? {
        return monthlyPlanDao.getPlanByMonth(month)
    }

    fun insertPlan(plan: MonthlyPlan) {
        monthlyPlanDao.insertPlan(plan)
    }

    fun insertPlans(plans: List<MonthlyPlan>) {
        monthlyPlanDao.insertPlans(plans)
    }

    fun updatePlan(plan: MonthlyPlan) {
        monthlyPlanDao.updatePlan(plan)
    }

    fun deletePlan(plan: MonthlyPlan) {
        monthlyPlanDao.deletePlan(plan)
    }
}
