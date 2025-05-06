package com.app.pmc.core.ui.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pmc.core.ui.R

@Composable
fun TopBarWithBackButton(
    topBarTitle: String = "",
    popToBackStack: () -> Unit = {},
    modifier: Modifier = Modifier,
    trailingIcon: @Composable () -> Unit = {},
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(top = 54.dp, start = 20.dp, end = 20.dp),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.clickable {
                popToBackStack()
            },
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = topBarTitle,
            fontSize = 17.sp,
            fontWeight = FontWeight.W600
        )
        trailingIcon.invoke()
    }
}


@Composable
@Preview
fun TopBarWithBackButtonPreview() {
    TopBarWithBackButton(
        topBarTitle = stringResource(R.string.vote_list_title),
        horizontalArrangement = Arrangement.SpaceBetween,
        trailingIcon = {
            Text(
                text = "test"
            )
        }
    )
}