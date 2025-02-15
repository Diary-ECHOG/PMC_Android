package com.app.pmc.feat.mypage.withdraw

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.topbar.TopBarWithBackButton
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun WithdrawScreen(
    modifier: Modifier = Modifier,
    viewModel: WithdrawViewModel
) {
    val state by viewModel.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarWithBackButton(
                topBarTitle = stringResource(R.string.withdraw_title),
                modifier = modifier.padding(bottom = 25.dp)
            )
        },
        content = { innerPadding ->
            WithdrawConfirmScreen(
                modifier = modifier.padding(innerPadding),
                state = state,
                onReasonSelected = viewModel::onReasonSelected
            )
        }
    )
}

@Composable
@Preview
fun WithdrawScreenPreview() {
    WithdrawScreen(
        viewModel = WithdrawViewModel()
    )
}