package com.app.pmc.feat.diary

import java.time.LocalDateTime

data class AddDiaryScreenState(
    val date: LocalDateTime? = LocalDateTime.now(),
    val title: String = "",
    val content: String = "",
    val voteCount: Int = 0
)
