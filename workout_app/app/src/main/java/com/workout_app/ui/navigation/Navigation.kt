package com.workout_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.workout_app.ui.screens.exercises.ExercisesScreen
import com.workout_app.ui.screens.home.HomeScreen
import com.workout_app.ui.screens.profile.ProfileScreen
import com.workout_app.ui.screens.schedule.ScheduleScreen
import com.workout_app.ui.screens.workouts.CreateWorkoutScreen
import com.workout_app.ui.screens.workouts.WorkoutsScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Exercises : Screen("exercises")
    object Workouts : Screen("workouts")
    object CreateWorkout : Screen("createWorkout")
    object Schedule : Screen("schedule")
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToExercises = { navController.navigate(Screen.Exercises.route) },
                onNavigateToWorkouts = { navController.navigate(Screen.Workouts.route) },
                onNavigateToSchedule = { navController.navigate(Screen.Schedule.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(Screen.Exercises.route) {
            ExercisesScreen()
        }

        composable(Screen.Workouts.route) {
            WorkoutsScreen(
                onNavigateToCreateWorkout = { navController.navigate(Screen.CreateWorkout.route) },
                onNavigateToWorkoutDetail = {}
            )
        }

        composable(Screen.CreateWorkout.route) {
            CreateWorkoutScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Schedule.route) {
            ScheduleScreen()
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateToSchedule = { navController.navigate(Screen.Schedule.route) }
            )
        }
    }
}
