package com.app.pmc.data.model

data class DiaryListResponse (
    val content: DiaryResponse,
    val pageNumber: Int,
    val pageSize: Int,
    val totalElements: Int,
    val totalPages: Int
)

data class DiaryResponse (
    val id: Int,
    val title: String,
    val content: String,
    val voteCount: Int,
    val createAt: String
)