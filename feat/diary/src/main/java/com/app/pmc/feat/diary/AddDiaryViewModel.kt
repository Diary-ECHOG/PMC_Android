package com.app.pmc.feat.diary

import androidx.lifecycle.ViewModel
import com.app.pmc.core.model.Diary
import com.app.pmc.core.model.ErrorResult
import com.app.pmc.core.model.ErrorType
import com.app.pmc.core.model.SuccessResult
import com.app.pmc.core.usecase.CreateDiaryUseCase
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
class AddDiaryViewModel @Inject constructor(
    private val createDiaryUseCase: CreateDiaryUseCase
) : ViewModel(),
    ContainerHost<AddDiaryScreenState, AddDiarySideEffect> {
    override val container: Container<AddDiaryScreenState, AddDiarySideEffect> = container(
        AddDiaryScreenState()
    )

    fun onTitleChange(title: String) = blockingIntent {
        reduce {
            state.copy(
                title = title
            )
        }
    }

    fun onBottomSheetTitleChanged(title: String) = blockingIntent {
        reduce {
            state.copy(
                bottomSheetState = state.bottomSheetState.copy(
                    title = title
                )
            )
        }
    }

    fun onBottomSheetContentChanged(content: String) = blockingIntent {
        reduce {
            state.copy(
                bottomSheetState = state.bottomSheetState.copy(
                    content = content
                )
            )
        }
    }

    fun createDiary() = intent {
        createDiaryUseCase(
            diary = Diary(
                title = state.title,
                content = state.content,
            )
        ).collectLatest { result ->
            when (result) {
                is SuccessResult<*> -> postSideEffect(AddDiarySideEffect.PopToBackStack)
                is ErrorResult -> {
                    when (result.errorType) {
                        ErrorType.FORBIDDEN -> postSideEffect(AddDiarySideEffect.ShowLoginDialog)
                        else -> postSideEffect(AddDiarySideEffect.ShowToast(result.errorType.name))
                    }
                }
                else -> {}
            }
        }
    }

    fun onBottomSheetCategoryChanged(category: String) = blockingIntent {
        reduce {
            state.copy(
                bottomSheetState = state.bottomSheetState.copy(
                    selectedCategory = category
                )
            )
        }
    }

    fun addVote() {

    }

    fun toggleDuplicateSelection(allowDuplicateSelection: Boolean) = intent {
        reduce {
            state.copy(
                bottomSheetState = state.bottomSheetState.copy(
                    allowDuplicateSelection = allowDuplicateSelection
                )
            )
        }
    }

    fun addOption() = intent {
        reduce {
            state.copy(
                bottomSheetState = state.bottomSheetState.copy(
                    option = state.bottomSheetState.option.plus("")
                )
            )
        }
    }

    fun onContentChange(content: String) = blockingIntent {
        reduce {
            state.copy(
                content = content
            )
        }
    }
}

sealed class AddDiarySideEffect {
    data class ShowToast(val message: String) : AddDiarySideEffect()
    data object PopToBackStack : AddDiarySideEffect()
    data object ShowLoginDialog : AddDiarySideEffect()
}