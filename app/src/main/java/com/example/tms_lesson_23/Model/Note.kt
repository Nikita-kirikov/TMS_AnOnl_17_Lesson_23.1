package com.example.tms_lesson_23.Model

import java.util.UUID

data class Note(
    val header: String,
    val text: String,
    val important: Boolean = false,
    val id : String = UUID.randomUUID().toString()
)