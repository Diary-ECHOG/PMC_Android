package com.app.pmc.feat.diary

import java.time.LocalDateTime

data class AddDiaryScreenState(
    val date: LocalDateTime? = LocalDateTime.now(),
    val title: String = "",
    val content: String = "",
    val voteCount: Int = 0,
    val bottomSheetState: BottomSheetState = BottomSheetState()
)

data class BottomSheetState(
    val title: String = "",
    val content: String = "",
    val allowDuplicateSelection: Boolean = false,
    val requireOption: List<String> = listOf(),
    val categoryList: List<String> = listOf("1","2","3","4"),
    val voteOption: List<String> = listOf(),
    val selectedCategory: String = "",
    val option: List<String> = listOf("", "")
)