package com.app.pmc.feat.home

import androidx.lifecycle.ViewModel
import com.app.pmc.core.model.ErrorResult
import com.app.pmc.core.model.SuccessResult
import com.app.pmc.core.usecase.GetDiariesUseCase
import com.app.pmc.core.util.StringExtension.getMonth
import com.app.pmc.core.util.StringExtension.isToday
import com.app.pmc.core.util.StringExtension.isYesterday
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDiariesUseCase: GetDiariesUseCase
) : ViewModel(), ContainerHost<HomeUiState, Unit> {
    override val container: Container<HomeUiState, Unit> = container(HomeUiState())

    init {
        getDiaries()
    }

    private fun getDiaries() = intent {
        getDiariesUseCase().collectLatest { result ->
            when (result) {
                is SuccessResult -> {
                    result.data?.let { diaries ->
                        reduce {
                            state.copy(
                                todayDiary = diaries.find { it.date?.isToday() == true },
                                yesterdayDiary = diaries.find { it.date?.isYesterday() == true},
                                monthlyDiaryList = diaries.groupBy { it.date?.getMonth() }.map { (month, diaryList) ->
                                    MonthlyDiary(
                                        month = month ?: -1,
                                        diaryList = diaryList
                                    )
                                }
                            )
                        }
                    }
                }
                is ErrorResult -> {}
            }
        }
    }
}
