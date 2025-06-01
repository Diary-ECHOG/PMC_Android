package com.app.pmc.feat.home

import com.app.pmc.core.model.Diary

data class HomeUiState(
    val todayDiary: List<Diary>? = emptyList(),
    val yesterdayDiary: List<Diary>? = emptyList(),
    val monthlyDiaryList: List<MonthlyDiary> = emptyList()
)

data class MonthlyDiary(
    val month: Int,
    val diaryList: List<Diary> = emptyList(),
)
