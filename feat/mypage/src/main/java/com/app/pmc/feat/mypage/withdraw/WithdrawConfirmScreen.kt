package com.app.pmc.feat.mypage.withdraw

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.button.EchogButton
import com.app.pmc.core.ui.textfield.EchogBasicTextField
import com.app.pmc.core.ui.theme.Black
import com.app.pmc.core.ui.theme.Gray_100
import com.app.pmc.core.ui.theme.Withdraw_Confirm
import com.app.pmc.core.ui.theme.Withdraw_Info_Title

@Composable
fun WithdrawConfirmScreen(
    modifier: Modifier = Modifier,
    onWithdrawSentenceChanged: (String) -> Unit,
    withdrawSentence: String
) {
    Column(modifier = modifier) {
        Column(modifier.padding(18.dp)) {
            InfoTitle(
                title = stringResource(R.string.withdraw_info_title)
            )
            InfoDescription(
                description = stringResource(R.string.withdraw_info_content)
            )
        }
        Spacer(
            modifier = modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Gray_100)
        )
        Column(modifier.padding(18.dp)) {
            InfoTitle(
                title = stringResource(R.string.withdraw_confirm_title)
            )
            InfoDescription(
                color = Withdraw_Confirm,
                description = stringResource(R.string.withdraw_confirm_sentence)
            )
        }
        EchogBasicTextField(
            modifier = modifier.padding(horizontal = 18.dp),
            value = withdrawSentence,
            onValueChange = onWithdrawSentenceChanged,
            placeholder = stringResource(R.string.withdraw_confirm_sentence),
        )
        Spacer(modifier = modifier.weight(1f))
        EchogButton(
            modifier = modifier
                .padding(18.dp)
                .fillMaxWidth(),
            label = stringResource(R.string.withdraw_confirm_button_label),
            onClick = {},
            enabled = withdrawSentence == stringResource(R.string.withdraw_confirm_sentence)
        )
    }
}

@Composable
private fun InfoTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        modifier = modifier,
        fontWeight = FontWeight.W600,
        fontSize = 13.sp,
        text = title,
        color = Withdraw_Info_Title
    )
}

@Composable
private fun InfoDescription(
    modifier: Modifier = Modifier,
    color: Color = Black,
    description: String
) {
    Text(
        style = TextStyle(
            lineHeight = 28.sp,
        ),
        color = color,
        modifier = modifier.padding(top = 6.dp),
        text = description
    )
}

@Composable
@Preview(showBackground = true)
fun WithdrawConfirmScreenPreview() {
    WithdrawConfirmScreen(
        onWithdrawSentenceChanged = {},
        withdrawSentence = stringResource(R.string.withdraw_confirm_sentence)
    )
}