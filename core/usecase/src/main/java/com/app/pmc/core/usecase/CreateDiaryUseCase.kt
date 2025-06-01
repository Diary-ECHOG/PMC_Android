package com.app.pmc.core.usecase

import com.app.pmc.core.model.Diary
import com.app.pmc.core.model.EchogResult
import com.app.pmc.core.repository.DiaryRepository
import com.app.pmc.core.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateDiaryUseCase @Inject constructor(
    private val repository: DiaryRepository
) {
    operator fun invoke(diary: Diary): Flow<EchogResult<Boolean>> = repository.createDiary(
        title = diary.title,
        content = diary.content,
    )
}