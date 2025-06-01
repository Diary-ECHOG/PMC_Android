package com.app.pmc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DiaryRequest (
    val id: String,
    val title: String,
    val content: String,
)