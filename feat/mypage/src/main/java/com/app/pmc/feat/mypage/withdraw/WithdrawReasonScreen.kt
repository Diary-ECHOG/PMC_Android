package com.app.pmc.feat.mypage.withdraw

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.textfield.EchogBasicTextField
import com.app.pmc.core.ui.theme.Checkbox_UnChecked
import com.app.pmc.core.ui.theme.Gray_100

@Composable
fun WithdrawReasonScreen(
    modifier: Modifier = Modifier,
    state: WithdrawState,
    onReasonSelected: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        item {
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Gray_100)
                    .padding(bottom = 13.dp)
            )
        }
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

@Composable
private fun WithdrawReasonItem(
    modifier: Modifier = Modifier,
    reason: String,
    isSelected: Boolean,
    onReasonSelected: () -> Unit
) {
    val isEtcField = reason == stringResource(R.string.withdraw_reason_7)
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
                modifier = Modifier.padding(top = 13.dp, end = 18.dp, bottom = 13.dp),
                value = "",
                placeholder = stringResource(R.string.withdraw_reason_placeholder),
                enabled = isSelected,
                onValueChange = {},
                defaultMinHeight = 100.dp
            )
        }
    }
    Spacer(
        modifier = modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Gray_100)
    )
}

@Composable
@Preview(showBackground = true)
fun WithdrawReasonScreenPreview() {
    WithdrawReasonScreen(
        state = WithdrawState(
            withdrawReasonList = listOf(
                stringResource(R.string.withdraw_reason_1),
                stringResource(R.string.withdraw_reason_2),
                stringResource(R.string.withdraw_reason_3),
                stringResource(R.string.withdraw_reason_4),
                stringResource(R.string.withdraw_reason_5),
                stringResource(R.string.withdraw_reason_6),
                stringResource(R.string.withdraw_reason_7)
            ),
            selectedReason = stringResource(R.string.withdraw_reason_1)
        ),
        onReasonSelected = {}
    )
}