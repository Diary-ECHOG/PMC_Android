package com.app.pmc.feat.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.button.EchogButton
import com.app.pmc.core.ui.surface.GradientSurface
import com.app.pmc.core.ui.textfield.EchogBasicTextField
import com.app.pmc.core.ui.textfield.EchogPasswordTextField
import com.app.pmc.ui.theme.LargeDescription

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
) {
    GradientSurface {
        LazyColumn(
            modifier = modifier.padding(horizontal = 20.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text(
                    modifier = Modifier.padding(bottom = 34.dp),
                    style = LargeDescription,
                    text = stringResource(R.string.login)
                )
            EchogBasicTextField(
                modifier = Modifier.padding(bottom = 14.dp),
                label = stringResource(R.string.user_info_email),
                placeholder = stringResource(R.string.user_info_email),
                value = "",
                onValueChange = {},
            )
            EchogPasswordTextField(
                label = stringResource(R.string.user_info_password),
                placeholder = stringResource(R.string.user_info_placeholder_password_rule),
                value = "",
                onValueChange = {},
                supportingText = stringResource(R.string.find_my_password),
                supportingTextAlign = TextAlign.End,
                )
                EchogButton(
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                    label = stringResource(R.string.next),
                    onClick = { }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}