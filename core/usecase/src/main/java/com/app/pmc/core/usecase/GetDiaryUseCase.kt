package com.app.pmc.core.usecase

import com.app.pmc.core.model.Diary
import com.app.pmc.core.model.EchogResult
import com.app.pmc.core.model.ErrorResult
import com.app.pmc.core.model.SuccessResult
import com.app.pmc.core.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDiaryUseCase @Inject constructor(
    private val repository: DiaryRepository
) {
    operator fun invoke(id: String): Flow<EchogResult<Diary?>> = repository.getDiary(
        diaryId = id
    )
}