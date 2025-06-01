package com.app.pmc.core.repository

import com.app.pmc.core.model.Diary
import com.app.pmc.core.model.EchogResult
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {
    fun createDiary(title: String, content: String): Flow<EchogResult<Boolean>>
    fun getDiaries(page: Int, size: Int, sort: String): Flow<EchogResult<List<Diary>?>>
    fun getDiary(diaryId: String): Flow<EchogResult<Diary?>>
}