package com.app.pmc.feat.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.button.EchogButton
import com.app.pmc.core.ui.surface.GradientSurface
import com.app.pmc.core.ui.textfield.EchogBasicTextField
import com.app.pmc.core.ui.textfield.EchogPasswordTextField
import com.app.pmc.core.ui.theme.LargeDescription
import com.app.pmc.core.ui.theme.Slate_600
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    showSnackBar: (String) -> Unit = {},
    navigateToMain: () -> Unit = {},
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is LoginSideEffect.ShowToast -> showSnackBar(sideEffect.toString())
            is LoginSideEffect.NavigateToMain -> navigateToMain()
        }
    }
    GradientSurface {
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
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
                    value = state.value.email,
                    onValueChange = viewModel::onEmailChanged,
                )
                EchogPasswordTextField(
                    label = stringResource(R.string.user_info_password),
                    placeholder = stringResource(R.string.user_info_placeholder_password_rule),
                    value = state.value.password,
                    onValueChange = viewModel::onPasswordChanged,
                    supportingTextAlign = TextAlign.End,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 12.dp),
                        text = stringResource(R.string.find_my_password),
                        textAlign = TextAlign.End,
                        color = Slate_600,
                        fontSize = 12.sp
                    )
                    Text(
                        modifier = Modifier
                            .padding(vertical = 12.dp),
                        text = stringResource(R.string.join_button_label),
                        textAlign = TextAlign.End,
                        color = Slate_600,
                        fontSize = 12.sp
                    )
                }
                EchogButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = stringResource(R.string.next),
                    onClick = viewModel::login
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