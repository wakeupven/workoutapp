package com.workout_app.ui.screens.home

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
import com.workout_app.data.model.Exercise
import com.workout_app.data.model.ExerciseDifficulty

@Composable
fun HomeScreen(
    onNavigateToExercises: () -> Unit,
    onNavigateToWorkouts: () -> Unit,
    onNavigateToSchedule: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    var exerciseList by remember { mutableStateOf(emptyList<Exercise>()) }

    LaunchedEffect(Unit) {
        exerciseList = listOf(
            Exercise(
                id = "1",
                name = "Приседания",
                description = "Основное упражнение для ног",
                difficulty = ExerciseDifficulty.MEDIUM,
                targetMuscles = listOf("квадрицепс", "ягодицы", "спина"),
                equipment = listOf("глубокий", "гантели"),
                sets = 4,
                reps = 8,
                restTime = 90,
                imageUrls = listOf("https://example.com/squat.jpg")
            ),
            Exercise(
                id = "2",
                name = "Жим лежа",
                description = "Классическое упражнение для груди",
                difficulty = ExerciseDifficulty.HARD,
                targetMuscles = listOf("грудные", "трицепс", "передняя дельта"),
                equipment = listOf("гантели", "барабаны"),
                sets = 4,
                reps = 6,
                restTime = 120,
                imageUrls = listOf("https://example.com/bench_press.jpg")
            ),
            Exercise(
                id = "3",
                name = "Подтягивания",
                description = "Упражнение для спины и бицепса",
                difficulty = ExerciseDifficulty.MEDIUM,
                targetMuscles = listOf("спина", "бицепс"),
                equipment = listOf("турник"),
                sets = 3,
                reps = 10,
                restTime = 60,
                imageUrls = listOf("https://example.com/pull_ups.jpg")
            ),
            Exercise(
                id = "4",
                name = "Отжимания",
                description = "Базовое упражнение для верхней части тела",
                difficulty = ExerciseDifficulty.EASY,
                targetMuscles = listOf("грудные", "трицепс", "передняя дельта"),
                equipment = listOf("тело"),
                sets = 3,
                reps = 15,
                restTime = 60,
                imageUrls = listOf("https://example.com/push_ups.jpg")
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
                restTime = 180,
                imageUrls = listOf("https://example.com/deadlift.jpg")
            ),
            Exercise(
                id = "6",
                name = "Планка",
                description = "Упражнение для укрепления кора",
                difficulty = ExerciseDifficulty.EASY,
                targetMuscles = listOf("пресс", "спина", "плечи"),
                equipment = listOf("тело"),
                sets = 3,
                reps = 30,
                restTime = 30,
                imageUrls = listOf("https://example.com/plank.jpg")
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
                restTime = 90,
                imageUrls = listOf("https://example.com/leg_press.jpg")
            ),
            Exercise(
                id = "8",
                name = "Скручивания",
                description = "Упражнение для пресса",
                difficulty = ExerciseDifficulty.EASY,
                targetMuscles = listOf("пресс"),
                equipment = listOf("тело"),
                sets = 4,
                reps = 15,
                restTime = 45,
                imageUrls = listOf("https://example.com/crunches.jpg")
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Тренировка") },
                navigationIcon = {
                    IconButton(onClick = onNavigateToProfile) {
                        Icon(Icons.Filled.Person, contentDescription = "Профиль")
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

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    ExerciseCard(
                        exercise = exerciseList.firstOrNull() ?: Exercise(
                            id = "1",
                            name = "Приседания",
                            description = "Основное упражнение для ног",
                            difficulty = ExerciseDifficulty.MEDIUM,
                            targetMuscles = listOf("квадрицепс", "ягодицы", "спина"),
                            equipment = listOf("глубокий", "гантели"),
                            sets = 4,
                            reps = 8,
                            restTime = 90,
                            imageUrls = listOf("https://example.com/squat.jpg")
                        ),
                        modifier = Modifier.weight(1f),
                        onNavigateToExercise = {
                            onNavigateToExercises()
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(exerciseList.drop(1)) { exercise ->
                ExerciseCard(
                    exercise = exercise,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    onNavigateToExercise = {
                        onNavigateToExercises()
                    }
                )
            }
        }
    }
}

@Composable
private fun ExerciseCard(
    exercise: Exercise,
    modifier: Modifier = Modifier,
    onNavigateToExercise: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onNavigateToExercise() },
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
                        text = exercise.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = exercise.description,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Chip(
                    label = {
                        Text(
                            text = when (exercise.difficulty) {
                                ExerciseDifficulty.EASY -> "Легко"
                                ExerciseDifficulty.MEDIUM -> "Средне"
                                ExerciseDifficulty.HARD -> "Сложно"
                            }
                        )
                    },
                    colors = ChipDefaults.primaryContainerChipColors
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (exercise.targetMuscles.isNotEmpty()) {
                    Chip(
                        label = {
                            Text(
                                text = "${exercise.targetMuscles.size} мышц",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    )
                }
                if (exercise.equipment.isNotEmpty()) {
                    Chip(
                        label = {
                            Text(
                                text = "${exercise.equipment.size} типа",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    )
                }
            }
        }
    }
}
