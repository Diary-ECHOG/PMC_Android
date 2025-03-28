package com.app.pmc.feat.auth

import androidx.lifecycle.ViewModel
import com.app.pmc.core.usecase.SendCodeUseCase
import com.app.pmc.core.usecase.VerifyUseCase
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
class UserInfoViewModel @Inject constructor(
    val sendCodeUseCase: SendCodeUseCase,
    val onVerifyUseCase: VerifyUseCase
) : ViewModel(), ContainerHost<UserInfoState, Event> {
    override val container: Container<UserInfoState, Event> = container(UserInfoState())


    fun onPhoneNumberChanged(name: String) = blockingIntent {
        reduce {
            state.copy(
                phoneNumber = name,
                isPhoneNumberValid = name.length == 11
            )
        }
    }

    fun onVerifyNumberChanged(number: String) = blockingIntent {
        reduce {
            state.copy(
                verifyNumber = number,
            )
        }
    }

    fun onPasswordChanged(password: String) = blockingIntent {
        reduce {
            state.copy(
                password = password
            )
        }
    }

    fun onPasswordVerifyChanged(password: String) = blockingIntent {
        reduce {
            state.copy(
                confirmPassword = password
            )
        }
    }

    fun onEmailChanged(email: String) = blockingIntent {
        reduce {
            state.copy(
                email = email
            )
        }
    }

    fun onSendCode() = intent {
        sendCodeUseCase(state.email).collectLatest {
            postSideEffect(Event.Toast(it))
        }
    }

    fun onVerify() = intent {
        onVerifyUseCase(token = state.verifyNumber, email = state.email).collectLatest {
            postSideEffect(Event.Toast(it))
        }
    }
}

data class UserInfoState(
    val phoneNumber: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPhoneNumberValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isConfirmPasswordValid: Boolean = false,
    val verifyNumber: String = "",
    val isFormValid: Boolean = false,
    val isSubmitting: Boolean = false,
    val isSubmitted: Boolean = false,
    val errorMessage: String = "",
)

sealed class Event {
    data class Toast(
        val message: String
    ) : Event()
}