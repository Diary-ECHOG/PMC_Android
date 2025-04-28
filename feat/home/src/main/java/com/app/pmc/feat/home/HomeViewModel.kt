package com.app.pmc.feat.home

import androidx.lifecycle.ViewModel
import com.app.pmc.core.model.Diary
import com.app.pmc.core.model.EchogResult
import com.app.pmc.core.usecase.GetDiariesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDiariesUseCase: GetDiariesUseCase
): ViewModel(), ContainerHost<HomeUiState, Unit> {
    override val container: Container<HomeUiState, Unit> = container(HomeUiState())

    init {
        getDiaries()
    }

    private fun getDiaries() = intent {
        getDiariesUseCase().collectLatest { result ->
            when (result) {
                 result
            }
        }
    }
}