package com.app.pmc.core.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class Diary (
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val content: String = "",
    val date: String? = LocalDateTime.now().toString()
)