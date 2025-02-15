package com.app.pmc.feat.mypage.withdraw

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class WithdrawViewModel @Inject constructor(
    val resourceProvider: ResourceProvider
) : ViewModel(), ContainerHost<WithdrawState, Unit> {
    override val container: Container<WithdrawState, Unit> = container(WithdrawState())

    init {
        getWithdrawReasonList()
    }

    private fun getWithdrawReasonList() = intent {
        reduce {
            state.copy(
                withdrawReasonList = listOf(
                    resourceProvider.getString(R.string.withdraw_reason_1),
                    resourceProvider.getString(R.string.withdraw_reason_2),
                    resourceProvider.getString(R.string.withdraw_reason_3),
                    resourceProvider.getString(R.string.withdraw_reason_4),
                    resourceProvider.getString(R.string.withdraw_reason_5),
                    resourceProvider.getString(R.string.withdraw_reason_6),
                    resourceProvider.getString(R.string.withdraw_reason_7),
                )
            )
        }
    }

    fun onReasonSelected(reason: String) = intent {
        reduce {
            state.copy(selectedReason = reason)
        }
    }

    fun onWithdrawSentenceChanged(sentence: String) = blockingIntent {
        reduce {
            state.copy(withdrawSentence = sentence)
        }
    }
}

data class WithdrawState(
    val withdrawReasonList: List<String> = emptyList(),
    val selectedReason: String = "",
    val withdrawSentence: String = ""
)