package com.app.pmc.feat.auth.login

import androidx.lifecycle.ViewModel
import com.app.pmc.core.model.EchogResult
import com.app.pmc.core.model.ErrorResult
import com.app.pmc.core.model.SuccessResult
import com.app.pmc.core.usecase.SendResetPasswordEmailUseCase
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
class FindPasswordViewModel @Inject constructor(
    val sendResetPasswordEmailUseCase: SendResetPasswordEmailUseCase
) : ViewModel(), ContainerHost<FindPasswordUiState, FindPasswordSideEffect> {
    override val container: Container<FindPasswordUiState, FindPasswordSideEffect> = container(FindPasswordUiState())

    fun sendResetPasswordMail() = intent {
        sendResetPasswordEmailUseCase(state.email).collectLatest { result ->
            when (result) {
                is SuccessResult<*> -> postSideEffect(FindPasswordSideEffect.NavigateToBackstack)
                is ErrorResult -> postSideEffect(FindPasswordSideEffect.ShowToast(result.message.toString()))
            }
        }
    }

    fun onEmailChanged(email: String) = blockingIntent {
        reduce {
            state.copy(email = email)
        }
    }
}

data class FindPasswordUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed class FindPasswordSideEffect {
    data class ShowToast(val message: String) : FindPasswordSideEffect()
    data object NavigateToBackstack : FindPasswordSideEffect()
}