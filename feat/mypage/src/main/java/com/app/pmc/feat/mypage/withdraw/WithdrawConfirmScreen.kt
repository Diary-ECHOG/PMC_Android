package com.app.pmc.feat.mypage.withdraw

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pmc.core.ui.topbar.TopBarWithBackButton
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.textfield.EchogBasicTextField
import com.app.pmc.core.ui.theme.Checkbox_UnChecked
import com.app.pmc.core.ui.theme.Gray_100

@Composable
fun WithdrawConfirmScreen(
    modifier: Modifier = Modifier,
    state: WithdrawState,
    onReasonSelected: (String) -> Unit
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
            LazyColumn(modifier = modifier.padding(innerPadding)) {
                items(
                    count = state.withdrawReasonList.size,
                    key = { index -> state.withdrawReasonList[index] },
                    itemContent = { index ->
                        WithdrawReasonItem(
                            reason = state.withdrawReasonList[index],
                            isSelected = state.withdrawReasonList[index] == state.selectedReason,
                            onReasonSelected = { onReasonSelected(state.withdrawReasonList[index]) }
                        )
                    }
                )
            }
        }
    )
}

@Composable
private fun WithdrawReasonItem(
    modifier: Modifier = Modifier,
    reason: String,
    isSelected: Boolean,
    onReasonSelected: () -> Unit
) {
    val isEtcField = reason == stringResource(R.string.withdraw_reason_7)
    Spacer(
        modifier = modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Gray_100)
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(1.dp),
        verticalAlignment = if (isEtcField) Alignment.Top else Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = modifier.clip(shape = RoundedCornerShape(4.dp)),
            colors = CheckboxDefaults.colors(
                uncheckedColor = Checkbox_UnChecked
            ),
            checked = isSelected,
            onCheckedChange = { onReasonSelected() }
        )
        Column {
            Text(
                modifier = Modifier.padding(start = 8.dp, top = if (isEtcField) 13.dp else 0.dp),
                text = reason
            )
            if (isEtcField) EchogBasicTextField(
                modifier = Modifier.padding(top = 13.dp, end = 18.dp),
                value = "",
                placeholder = stringResource(R.string.withdraw),
                enabled = isSelected,
                onValueChange = {},
                defaultMinHeight = 100.dp
            )
        }
    }
}

@Composable
@Preview
fun WithdrawConfirmScreenPreview() {
    WithdrawConfirmScreen(
        state = WithdrawState(
            selectedReason = stringResource(R.string.withdraw_reason_1)
        ),
        onReasonSelected = {}
    )
}