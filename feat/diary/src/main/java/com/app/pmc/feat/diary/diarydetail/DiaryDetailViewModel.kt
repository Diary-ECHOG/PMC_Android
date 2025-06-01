package com.app.pmc.feat.diary.diarydetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.app.pmc.core.model.Diary
import com.app.pmc.core.model.EchogResult
import com.app.pmc.core.model.SuccessResult
import com.app.pmc.core.usecase.CreateDiaryUseCase
import com.app.pmc.core.usecase.GetDiariesUseCase
import com.app.pmc.core.usecase.GetDiaryUseCase
import com.app.pmc.core.util.StringExtension.toDiaryDate
import com.app.pmc.feat.diary.AddDiarySideEffect
import com.app.pmc.feat.diary.navigation.ROUTE_DIARY_DETAIL_ARGUMENT_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DiaryDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getDiaryUseCase: GetDiaryUseCase
) : ViewModel(),
    ContainerHost<DiaryDetailScreenState, DiaryDetailSideEffect> {
    override val container: Container<DiaryDetailScreenState, DiaryDetailSideEffect> = container(
        DiaryDetailScreenState()
    )
    val id: String = savedStateHandle.get<String>(ROUTE_DIARY_DETAIL_ARGUMENT_ID) ?: ""

    init {
        getDiary(id)
    }

    private fun getDiary(id: String) = intent {
        getDiaryUseCase(id).collectLatest { result ->
            when (result) {
                is SuccessResult -> reduce {
                    val diary = result.data
                    state.copy(
                        date = diary?.date?.toDiaryDate(),
                        title = diary?.title,
                        content = diary?.content,
                    )
                }
                else -> {}
            }
        }
    }
}

sealed class DiaryDetailSideEffect {
    data class ShowToast(val message: String) : DiaryDetailSideEffect()
    data object PopToBackStack : DiaryDetailSideEffect()
}