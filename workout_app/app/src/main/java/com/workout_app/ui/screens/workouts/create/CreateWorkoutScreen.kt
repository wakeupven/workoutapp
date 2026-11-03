package com.workout_app.ui.screens.workouts.create

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun CreateWorkoutScreen(
    onNavigateBack: () -> Unit
) {
    var workoutName by remember { mutableStateOf("") }
    var workoutDescription by remember { mutableStateOf("") }
    var workoutNotes by remember { mutableStateOf("") }
    var selectedDifficulty by remember { mutableStateOf<ExerciseDifficulty>(ExerciseDifficulty.MEDIUM) }
    var selectedExercises by remember { mutableStateOf<List<Exercise>>(emptyList()) }
    var showExerciseSelector by remember { mutableStateOf(false) }

    var allExercises by remember {
        mutableStateOf(listOf(
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
        ))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Создать тренировку") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (workoutName.isNotBlank() && selectedExercises.isNotEmpty()) {
                        // Сохранить тренировку
                    }
                },
                enabled = workoutName.isNotBlank() && selectedExercises.isNotEmpty()
            ) {
                Icon(Icons.Filled.Save, contentDescription = "Сохранить")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    text = "Название",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = workoutName,
                    onValueChange = { workoutName = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Введите название тренировки") }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    text = "Описание",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = workoutDescription,
                    onValueChange = { workoutDescription = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Введите описание тренировки") }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    text = "Примечания",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = workoutNotes,
                    onValueChange = { workoutNotes = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Введите примечания") }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    text = "Сложность",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ExerciseDifficulty.entries.forEach { difficulty ->
                        FilterChip(
                            label = { Text(when (difficulty) {
                                ExerciseDifficulty.EASY -> "Легко"
                                ExerciseDifficulty.MEDIUM -> "Средне"
                                ExerciseDifficulty.HARD -> "Сложно"
                            }) },
                            selected = selectedDifficulty == difficulty,
                            onClick = { selectedDifficulty = difficulty }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    text = "Упражнения",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Выберите упражнения для тренировки",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (selectedExercises.isEmpty()) {
                    OutlinedButton(
                        onClick = { showExerciseSelector = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Выбрать упражнения")
                    }
                } else {
                    Column {
                        selectedExercises.forEach { exercise ->
                            WorkoutExerciseCard(
                                exercise = exercise,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    if (showExerciseSelector) {
        ExerciseSelectorDialog(
            exercises = allExercises,
            selectedExercises = selectedExercises,
            onExerciseSelected = { exercise ->
                selectedExercises = if (exercise in selectedExercises) {
                    selectedExercises - exercise
                } else {
                    selectedExercises + exercise
                }
            },
            onDismiss = { showExerciseSelector = false }
        )
    }
}

@Composable
private fun ExerciseSelectorDialog(
    exercises: List<Exercise>,
    selectedExercises: List<Exercise>,
    onExerciseSelected: (Exercise) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Выберите упражнения") },
        text = {
            LazyColumn {
                items(exercises) { exercise ->
                    WorkoutExerciseCard(
                        exercise = exercise,
                        isSelected = exercise in selectedExercises,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .clickable { onExerciseSelected(exercise) },
                        onNavigateToExercise = {}
                    )
                }
            }
        },
        confirmButton = {
            OutlinedButton(
                onClick = onDismiss
            ) {
                Text("Закрыть")
            }
        }
    )
}

@Composable
private fun WorkoutExerciseCard(
    exercise: Exercise,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    onNavigateToExercise: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onNavigateToExercise() },
        colors = if (isSelected) CardDefaults.selectedItemColors() else CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
            FilterChip(
                label = {
                    Text(
                        text = when (exercise.difficulty) {
                            ExerciseDifficulty.EASY -> "Легко"
                            ExerciseDifficulty.MEDIUM -> "Средне"
                            ExerciseDifficulty.HARD -> "Сложно"
                        }
                    )
                },
                colors = if (isSelected) ChipDefaults.primaryContainerChipColors else ChipDefaults.secondaryContainerChipColors
            )
        }
    }
}
