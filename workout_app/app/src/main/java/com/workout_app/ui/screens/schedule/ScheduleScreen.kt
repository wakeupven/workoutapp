package com.workout_app.ui.screens.schedule

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
fun ScheduleScreen() {
    val weeklySchedule = remember {
        listOf(
            ScheduleItem(day = "Понедельник", exercises = emptyList()),
            ScheduleItem(day = "Вторник", exercises = emptyList()),
            ScheduleItem(day = "Среда", exercises = emptyList()),
            ScheduleItem(day = "Четверг", exercises = emptyList()),
            ScheduleItem(day = "Пятница", exercises = emptyList()),
            ScheduleItem(day = "Суббота", exercises = emptyList()),
            ScheduleItem(day = "Воскресенье", exercises = emptyList())
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Расписание") },
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

            items(weeklySchedule) { schedule ->
                ScheduleItemCard(
                    schedule = schedule,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun ScheduleItemCard(
    schedule: ScheduleItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = schedule.day,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (schedule.exercises.isEmpty()) {
                Text(
                    text = "Нет упражнений на этот день",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            } else {
                Text(
                    text = "${schedule.exercises.size} упражнений:",
                    style = MaterialTheme.typography.bodyMedium
                )
                schedule.exercises.forEach { exercise ->
                    Spacer(modifier = Modifier.height(4.dp))
                    ExerciseCard(
                        exercise = exercise,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
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
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium
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
                }
            )
        }
    }
}

data class ScheduleItem(
    val day: String,
    val exercises: List<Exercise> = emptyList()
)
