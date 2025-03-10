package com.app.pmc.data.model

data class DiaryResponse (
    val id: Int,
    val title: String,
    val content: String,
    val voteCount: Int,
    val date: String
)