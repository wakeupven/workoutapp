package com.workout_app.ui.screens.workouts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.workout_app.data.model.Workout
import com.workout_app.data.model.ExerciseDifficulty

@Composable
fun WorkoutsScreen(
    onNavigateToCreateWorkout: () -> Unit,
    onNavigateToWorkoutDetail: (Workout) -> Unit
) {
    val workouts = remember {
        listOf(
            Workout(
                id = "1",
                name = "Грудь и трицепс",
                description = "Классический день грудных мышц",
                exercises = listOf(
                    Exercise(
                        id = "1",
                        name = "Жим лежа",
                        description = "Классическое упражнение для груди",
                        difficulty = ExerciseDifficulty.HARD,
                        targetMuscles = listOf("грудные", "трицепс", "передняя дельта"),
                        equipment = listOf("гантели", "барабаны"),
                        sets = 4,
                        reps = 6,
                        restTime = 120
                    ),
                    Exercise(
                        id = "2",
                        name = "Отжимания",
                        description = "Базовое упражнение для верхней части тела",
                        difficulty = ExerciseDifficulty.EASY,
                        targetMuscles = listOf("грудные", "трицепс", "передняя дельта"),
                        equipment = listOf("тело"),
                        sets = 3,
                        reps = 15,
                        restTime = 60
                    )
                ),
                difficulty = ExerciseDifficulty.MEDIUM
            ),
            Workout(
                id = "2",
                name = "Спина и бицепс",
                description = "День для развития спины и бицепса",
                exercises = listOf(
                    Exercise(
                        id = "3",
                        name = "Подтягивания",
                        description = "Упражнение для спины и бицепса",
                        difficulty = ExerciseDifficulty.MEDIUM,
                        targetMuscles = listOf("спина", "бицепс"),
                        equipment = listOf("турник"),
                        sets = 3,
                        reps = 10,
                        restTime = 60
                    ),
                    Exercise(
                        id = "5",
                        name = "Становая тяга",
                        description = "Король всех упражнений",
                        difficulty = ExerciseDifficulty.HARD,
                        targetMuscles = listOf("спина", "бицепс", "квадрицепс", "ягодицы"),
                        equipment = listOf("гантели"),
                        sets = 3,
                        reps = 5,
                        restTime = 180
                    )
                ),
                difficulty = ExerciseDifficulty.HARD
            ),
            Workout(
                id = "3",
                name = "Ноги",
                description = "День для развития ног и ягодиц",
                exercises = listOf(
                    Exercise(
                        id = "1",
                        name = "Приседания",
                        description = "Основное упражнение для ног",
                        difficulty = ExerciseDifficulty.MEDIUM,
                        targetMuscles = listOf("квадрицепс", "ягодицы", "спина"),
                        equipment = listOf("глубокий", "гантели"),
                        sets = 4,
                        reps = 8,
                        restTime = 90
                    ),
                    Exercise(
                        id = "7",
                        name = "Жим ногами",
                        description = "Упражнение для ног в тренажере",
                        difficulty = ExerciseDifficulty.MEDIUM,
                        targetMuscles = listOf("квадрицепс", "ягодицы"),
                        equipment = listOf("гантели"),
                        sets = 4,
                        reps = 10,
                        restTime = 90
                    )
                ),
                difficulty = ExerciseDifficulty.MEDIUM
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Тренировки") },
                actions = {
                    IconButton(onClick = onNavigateToCreateWorkout) {
                        Icon(Icons.Filled.Add, contentDescription = "Создать")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(workouts) { workout ->
                WorkoutCard(
                    workout = workout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    onNavigateToWorkout = { onNavigateToWorkoutDetail(workout) }
                )
            }
        }
    }
}

@Composable
private fun WorkoutCard(
    workout: Workout,
    modifier: Modifier = Modifier,
    onNavigateToWorkout: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onNavigateToWorkout() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = workout.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = workout.description,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Chip(
                    label = {
                        Text(
                            text = when (workout.difficulty) {
                                ExerciseDifficulty.EASY -> "Легко"
                                ExerciseDifficulty.MEDIUM -> "Средне"
                                ExerciseDifficulty.HARD -> "Сложно"
                            }
                        )
                    },
                    colors = ChipDefaults.primaryContainerChipColors
                )
            }
            Text(
                text = "${workout.exercises.size} упражнений",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}
