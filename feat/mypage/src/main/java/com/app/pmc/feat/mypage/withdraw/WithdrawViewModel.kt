package com.app.pmc.feat.mypage.withdraw

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.app.pmc.core.ui.R
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container


class WithdrawViewModel : ViewModel(), ContainerHost<WithdrawState, Unit> {
    override val container: Container<WithdrawState, Unit> = container(WithdrawState())

    fun onReasonSelected(reason: String) = intent {
        reduce {
            state.copy(selectedReason = reason)
        }
    }
    fun onWithdrawSentenceChanged(sentence: String) = intent {
        reduce {
            state.copy(withdrawSentence = sentence)
        }
    }
}

data class WithdrawState(
    val withdrawReasonList: List<String> = listOf(
        Resources.getSystem().getString(R.string.withdraw_reason_1),
        Resources.getSystem().getString(R.string.withdraw_reason_2),
        Resources.getSystem().getString(R.string.withdraw_reason_3),
        Resources.getSystem().getString(R.string.withdraw_reason_4),
        Resources.getSystem().getString(R.string.withdraw_reason_5),
        Resources.getSystem().getString(R.string.withdraw_reason_6),
        Resources.getSystem().getString(R.string.withdraw_reason_7),
    ),
    val selectedReason: String = "",
    val withdrawSentence: String = ""
)