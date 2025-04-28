package com.app.pmc.feat.auth.login

import androidx.lifecycle.ViewModel
import com.app.pmc.core.model.EchogResult
import com.app.pmc.core.usecase.LoginUseCase
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
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase
) : ViewModel(), ContainerHost<LoginUiState, LoginSideEffect> {
    override val container: Container<LoginUiState, LoginSideEffect> = container(LoginUiState())

    fun login() = intent {
        loginUseCase(state.email, state.password).collectLatest { result ->
            when (result) {
                is SuccessResult<*><*> ->postSideEffect(LoginSideEffect.NavigateToMain)
                is ErrorResult -> postSideEffect(LoginSideEffect.ShowToast(result.message.toString()))
            }
        }
    }

    fun onEmailChanged(email: String) = blockingIntent {
        reduce {
            state.copy(email = email)
        }
    }

    fun onFindPasswordClicked() = intent {
        postSideEffect(LoginSideEffect.NavigateToFindPassword)
    }

    fun onPasswordChanged(password: String) = blockingIntent {
        reduce {
            state.copy(password = password)
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed class LoginSideEffect {
    data class ShowToast(val message: String) : LoginSideEffect()
    data object NavigateToMain : LoginSideEffect()
    data object NavigateToFindPassword : LoginSideEffect()
}