package com.workout_app.data.model

enum class MediaType {
    IMAGE,
    VIDEO
}

data class MediaItem(
    val type: MediaType,
    val url: String,
    val thumbnailUrl: String?,
    val duration: Long?
)
