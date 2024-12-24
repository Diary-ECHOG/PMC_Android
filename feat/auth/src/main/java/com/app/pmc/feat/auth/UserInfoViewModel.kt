package com.app.pmc.feat.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor() : ViewModel(), ContainerHost<UserInfoState, Unit> {
    override val container: Container<UserInfoState, Unit> = container(UserInfoState())

    init {

    }

    fun onPhoneNumberChanged(name: String) = blockingIntent {
        reduce {
            state.copy(
                phoneNumber = name,
                isphoneNumberValid = name.length == 11
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
}

data class UserInfoState(
    val phoneNumber: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isphoneNumberValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isConfirmPasswordValid: Boolean = false,
    val verifyNumber: String = "",
    val isFormValid: Boolean = false,
    val isSubmitting: Boolean = false,
    val isSubmitted: Boolean = false,
    val errorMessage: String = "",
)