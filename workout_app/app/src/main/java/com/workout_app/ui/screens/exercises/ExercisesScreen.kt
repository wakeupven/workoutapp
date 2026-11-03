package com.workout_app.ui.screens.exercises

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.workout_app.data.model.Exercise
import com.workout_app.data.model.ExerciseDifficulty

@Composable
fun ExercisesScreen() {
    val exercises = remember {
        listOf(
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
                id = "2",
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
                id = "4",
                name = "Отжимания",
                description = "Базовое упражнение для верхней части тела",
                difficulty = ExerciseDifficulty.EASY,
                targetMuscles = listOf("грудные", "трицепс", "передняя дельта"),
                equipment = listOf("тело"),
                sets = 3,
                reps = 15,
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
                restTime = 30
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
                restTime = 45
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Упражнения") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Назад")
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

            items(exercises) { exercise ->
                ExerciseCard(
                    exercise = exercise,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun ExerciseCard(
    exercise: Exercise,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
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
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Chip(
                    label = {
                        Text(
                            text = when (exercise.difficulty) {
                                ExerciseDifficulty.EASY -> "Легко"
                                ExerciseDifficulty.MEDIUM -> "Средне"
                                ExerciseDifficulty.HARD -> "Сложно"
                            }
                        )
                    }
                )
                if (exercise.targetMuscles.isNotEmpty()) {
                    Chip(
                        label = {
                            Text(
                                text = "${exercise.targetMuscles.size} мышц"
                            )
                        }
                    )
                }
            }
        }
    }
}
