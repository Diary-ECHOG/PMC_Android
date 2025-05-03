package com.app.pmc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DiaryListResponse (
    val content: List<DiaryResponse>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalElements: Int,
    val totalPages: Int
)

@Serializable
data class DiaryResponse (
    val id: String,
    val title: String,
    val content: String,
    val createdAt: String
)