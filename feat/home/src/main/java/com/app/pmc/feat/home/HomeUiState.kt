package com.app.pmc.feat.home

import com.app.pmc.core.model.Diary

data class HomeUiState(
    val todayDiary: Diary? = Diary(),
    val yesterdayDiary: Diary? = Diary(),
    val monthlyDiaryList: List<MonthlyDiary> = emptyList()
)

data class MonthlyDiary(
    val month: Int,
    val diaryList: List<Diary> = emptyList(),
)
