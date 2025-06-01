package com.app.pmc.feat.mypage

import androidx.lifecycle.ViewModel
import com.app.pmc.core.model.EchogResult
import com.app.pmc.core.model.SuccessResult
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.ResourceProvider
import com.app.pmc.core.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
) : ViewModel(), ContainerHost<Unit, MypageSideEffect> {
    override val container: Container<Unit, MypageSideEffect> = container(Unit)

    fun logout() = intent {
        logoutUseCase().collect { result ->
            when (result) {
                is SuccessResult -> {
                    println("logout success")
                    postSideEffect(MypageSideEffect.NavigateToLogin)
                }
                is EchogResult -> {

                }
            }
        }
    }
}

sealed class MypageSideEffect {
    data class ShowToast(val message: String) : MypageSideEffect()
    object NavigateToLogin : MypageSideEffect()
    object NavigateToMain : MypageSideEffect()
}