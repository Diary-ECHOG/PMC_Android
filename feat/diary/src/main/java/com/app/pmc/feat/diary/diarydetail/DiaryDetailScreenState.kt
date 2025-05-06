package com.app.pmc.feat.diary.diarydetail

import com.app.pmc.feat.diary.BottomSheetState
import java.time.LocalDateTime

data class DiaryDetailScreenState(
    val date: LocalDateTime? = LocalDateTime.now(),
    val title: String = "",
    val content: String = "",
    val voteCount: Int = 0,
    val bottomSheetState: BottomSheetState = BottomSheetState()
)