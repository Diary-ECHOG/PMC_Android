package com.app.pmc.feat.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.button.EchogButton
import com.app.pmc.core.ui.surface.GradientSurface
import com.app.pmc.core.ui.textfield.EchogBasicTextField
import com.app.pmc.core.ui.theme.LargeDescription
import com.app.pmc.core.ui.theme.Slate_700
import com.app.pmc.core.ui.theme.SmallDescription
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun FindPasswordScreen(
    modifier: Modifier = Modifier,
    showSnackBar: (String) -> Unit = {},
    navigateToBackStack: () -> Unit = {},
    viewModel: FindPasswordViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->

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
                    text = stringResource(R.string.find_password_title)
                )
                Text(
                    modifier = Modifier.padding(bottom = 34.dp),
                    color = Slate_700,
                    style = SmallDescription,
                    text = stringResource(R.string.find_password_description)
                )
                EchogBasicTextField(
                    modifier = Modifier.padding(bottom = 14.dp),
                    label = stringResource(R.string.user_info_email),
                    placeholder = stringResource(R.string.user_info_email),
                    value = state.value.email,
                    onValueChange = viewModel::onEmailChanged,
                )
                EchogButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = stringResource(R.string.reset_password),
                    onClick = viewModel::sendResetPasswordMail
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFindPasswordScreen() {
    FindPasswordScreen()
}