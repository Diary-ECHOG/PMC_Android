package com.app.pmc.feat.auth

import androidx.lifecycle.ViewModel
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.ResourceProvider
import com.app.pmc.core.usecase.SendCodeUseCase
import com.app.pmc.core.usecase.SignUpUseCase
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
    val onVerifyUseCase: VerifyUseCase,
    val signUpUseCase: SignUpUseCase,
    private val resourceProvider: ResourceProvider
) : ViewModel(), ContainerHost<UserInfoState, Event> {
    override val container: Container<UserInfoState, Event> = container(UserInfoState())

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

    fun onSignUp() = intent {
        try {
            if (state.confirmPassword != state.password) {
                postSideEffect(
                    Event.Toast(
                        resourceProvider.getString(
                            R.string.user_password_confirm_failed
                        )
                    )
                )
                return@intent
            }
            if (state.password.length < 8 || !state.password.contains(Regex("[a-zA-Z]")) || !state.password.contains(
                    Regex("[0-9]")
                )
            ) {
                postSideEffect(
                    Event.Toast(
                        resourceProvider.getString(
                            R.string.user_info_password_full_requirements_toast_message
                        )
                    )
                )
                return@intent
            }
            if (!state.isEmailValid) {
                postSideEffect(Event.Toast(resourceProvider.getString(R.string.user_email_verity_requires)))
                return@intent
            } else {
                signUpUseCase(
                    email = state.email,
                    password = state.password,
                    agreement = true,
                ).collectLatest {
                    postSideEffect(Event.Toast(it))
                }
            }
        } catch (e: Exception) {
            postSideEffect(Event.Toast(e.message.toString()))
        }
    }

    fun onSendCode() = intent {
        sendCodeUseCase(state.email).collectLatest {
            postSideEffect(Event.Toast(it))
        }
    }

    fun onVerify() = intent {
        onVerifyUseCase(token = state.verifyNumber, email = state.email).collectLatest {
            when (it) {
                true -> {
                    postSideEffect(Event.Toast(resourceProvider.getString(R.string.user_verify_success)))
                    reduce {
                        state.copy(
                            isEmailValid = true
                        )
                    }
                }

                false -> {
                    postSideEffect(Event.Toast(resourceProvider.getString(R.string.user_verify_failed)))
                }
            }
        }
    }
}

data class UserInfoState(
    val phoneNumber: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isEmailValid: Boolean = false,
    val verifyNumber: String = "",
)

sealed class Event {
    data class Toast(
        val message: String
    ) : Event()
}