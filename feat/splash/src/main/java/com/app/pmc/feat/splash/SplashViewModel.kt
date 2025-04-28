package com.app.pmc.feat.splash

import androidx.lifecycle.ViewModel
import com.app.pmc.core.model.EchogResult
import com.app.pmc.core.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val loginUseCase: LoginUseCase
) : ViewModel(), ContainerHost<SplashUiState, SplashSideEffect> {
    override val container: Container<SplashUiState, SplashSideEffect> = container((SplashUiState()))

    init {
        login()
    }

    private fun login() = intent {
        loginUseCase().collectLatest { result ->
            when (result) {
                is SuccessResult<*><*> -> postSideEffect(SplashSideEffect.NavigateToMain)
                is ErrorResult -> postSideEffect(SplashSideEffect.NavigateToLogin)
            }
        }
    }
}

data class SplashUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed class SplashSideEffect {
    data class ShowToast(val message: String) : SplashSideEffect()
    data object NavigateToMain : SplashSideEffect()
    data object NavigateToLogin : SplashSideEffect()
}