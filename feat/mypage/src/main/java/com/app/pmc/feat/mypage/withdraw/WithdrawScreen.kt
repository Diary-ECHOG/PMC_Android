package com.app.pmc.feat.mypage.withdraw

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.topbar.TopBarWithBackButton
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun WithdrawScreen(
    modifier: Modifier = Modifier,
    viewModel: WithdrawViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    WithdrawScreen(
        modifier = modifier,
        state = state,
        onReasonSelected = viewModel::onReasonSelected,
        onWithdrawSentenceChanged = viewModel::onWithdrawSentenceChanged
    )
}

@Composable
private fun WithdrawScreen(
    modifier: Modifier = Modifier,
    state: WithdrawState,
    onReasonSelected: (String) -> Unit,
    onWithdrawSentenceChanged: (String) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarWithBackButton(
                topBarTitle = stringResource(R.string.withdraw_title),
                modifier = modifier.padding(bottom = 25.dp)
            )
        },
        content = { innerPadding ->
//            WithdrawConfirmScreen(
//                modifier = modifier.padding(innerPadding),
//                onWithdrawSentenceChanged = onWithdrawSentenceChanged,
//                withdrawSentence = state.withdrawSentence
//            )
            WithdrawReasonScreen(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                state = state,
                onReasonSelected = onReasonSelected
            )
        }
    )
}

@Composable
@Preview
fun WithdrawScreenPreview() {
    WithdrawScreen(
        state = WithdrawState(
            withdrawReasonList = listOf(
                stringResource(R.string.withdraw_reason_1),
                stringResource(R.string.withdraw_reason_2),
                stringResource(R.string.withdraw_reason_3),
                stringResource(R.string.withdraw_reason_4),
                stringResource(R.string.withdraw_reason_5),
                stringResource(R.string.withdraw_reason_6),
                stringResource(R.string.withdraw_reason_7),
            ),
            selectedReason = stringResource(R.string.withdraw_reason_7),
            withdrawSentence = ""
        ),
        onReasonSelected = {},
        onWithdrawSentenceChanged = {}
    )
}