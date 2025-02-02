package com.app.pmc.feat.diary

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AddDiaryViewModel @Inject constructor() : ViewModel(),
    ContainerHost<AddDiaryScreenState, Unit> {
    override val container: Container<AddDiaryScreenState, Unit> = container(AddDiaryScreenState())

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