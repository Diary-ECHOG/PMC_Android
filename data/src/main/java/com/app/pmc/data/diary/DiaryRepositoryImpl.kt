package com.app.pmc.data.diary

import com.app.pmc.core.model.Diary
import com.app.pmc.core.model.EchogResult
import com.app.pmc.core.model.ErrorResult
import com.app.pmc.core.model.SuccessResult
import com.app.pmc.core.repository.DiaryRepository
import com.app.pmc.data.core.ApiResult
import com.app.pmc.data.di.SafeApiCall
import com.app.pmc.data.diary.DiaryMapper.toDomain
import com.app.pmc.data.model.DiaryRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val diaryService: DiaryService,
    private val safeApiCall: SafeApiCall
) : DiaryRepository {
    override fun createDiary(title: String, content: String): Flow<EchogResult<Boolean>> {
        return flow {
            when (val result = safeApiCall.execute {
                diaryService.saveDiary(
                    DiaryRequest(
                        id = UUID.randomUUID().toString(),
                        title = title,
                        content = content,
                    )
                )
            }) {
                is ApiResult.Success -> emit(SuccessResult(result.data.data != null))
                is ApiResult.Failure.HttpError -> emit(ErrorResult(result.getErrorMessage()))
                else -> emit(ErrorResult(result.exceptionOrNull()?.localizedMessage.toString()))
            }
        }
    }

    override fun getDiaries(page: Int, size: Int, sort: String): Flow<EchogResult<List<Diary>?>> {
        return flow {
            when (val result = safeApiCall.execute {
                diaryService.getDiaries(
                    page = page,
                    size = size,
                    sort = sort
                )
            }) {
                is ApiResult.Success -> emit(SuccessResult(result.data.toDomain()))
                is ApiResult.Failure.HttpError -> emit(ErrorResult(result.getErrorMessage()))
                else -> emit(ErrorResult(result.exceptionOrNull()?.localizedMessage.toString()))
            }
        }
    }
}