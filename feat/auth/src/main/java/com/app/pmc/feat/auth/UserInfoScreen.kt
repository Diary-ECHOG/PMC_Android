package com.app.pmc.feat.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.transition.Visibility
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.button.EchogButton
import com.app.pmc.core.ui.textfield.EchogBasicTextField
import com.app.pmc.core.ui.textfield.EchogPasswordTextField
import com.app.pmc.core.ui.theme.ButtonLabel500
import com.app.pmc.core.ui.theme.LargeDescription
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun UserInfoScreen(
    viewModel: UserInfoViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState()

    UserInfoScreen(
        state = state.value,
        onVerifyNumberChanged = viewModel::onVerifyNumberChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onPasswordVerifyChanged = viewModel::onPasswordVerifyChanged,
        onEmailChanged = viewModel::onEmailChanged
    )
}

@Composable
internal fun UserInfoScreen(
    modifier: Modifier = Modifier,
    state: UserInfoState = UserInfoState(),
    onVerifyNumberChanged: (String) -> Unit = { },
    onPasswordChanged: (String) -> Unit = { },
    onPasswordVerifyChanged: (String) -> Unit = { },
    onEmailChanged: (String) -> Unit = { }
) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        isVisible = true
        delay(2000L)
        isVisible = false
        delay(800L)
    }

    if (!isVisible) LazyColumn(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 117.dp, bottom = 28.dp),
                    style = LargeDescription,
                    text = stringResource(R.string.user_info_screen_title)
                )
                EmailField(
                    email = state.email,
                    onEmailChanged = onEmailChanged
                )
                VerifyNumberField(
                    verifyNumber = state.verifyNumber,
                    onVerifyNumberChanged = onVerifyNumberChanged
                )
                PasswordField(
                    password = state.password,
                    onPasswordChanged = onPasswordChanged
                )
                VerifyPasswordField(
                    confirmPassword = state.confirmPassword,
                    onPasswordVerifyChanged = onPasswordVerifyChanged
                )
            }
        }
        item {
            EchogButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                label = stringResource(R.string.next),
                onClick = { }
            )
        }
    }
    else SplashContent(
        isVisible = isVisible
    )
}

@Composable
private fun SplashContent(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(initialAlpha = 0.1f),
            exit = fadeOut(targetAlpha = 0.1f)
        ) {
            Text(
                text = stringResource(R.string.user_info_splash_content),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun VerifyNumberField(
    modifier: Modifier = Modifier,
    verifyNumber: String,
    onVerifyNumberChanged: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        EchogBasicTextField(
            modifier = modifier.weight(0.8f),
            label = stringResource(R.string.user_info_verify_number),
            placeholder = stringResource(R.string.user_info_placeholder_verify_number_required),
            value = verifyNumber,
            onValueChange = onVerifyNumberChanged,
        )
        EchogButton(
            modifier = modifier
                .weight(0.2f)
                .padding(start = 8.dp),
            label = stringResource(R.string.verify),
            labelStyle = ButtonLabel500,
            enabled = verifyNumber.isNotEmpty(),
            onClick = { }
        )
    }
}

@Composable
private fun PasswordField(
    password: String,
    onPasswordChanged: (String) -> Unit
) {
    EchogPasswordTextField(
        label = stringResource(R.string.user_info_password),
        placeholder = stringResource(R.string.user_info_placeholder_password_rule),
        value = password,
        onValueChange = onPasswordChanged,
    )
}

@Composable
private fun VerifyPasswordField(
    confirmPassword: String,
    onPasswordVerifyChanged: (String) -> Unit
) {
    EchogPasswordTextField(
        label = stringResource(R.string.user_info_confirm_password),
        value = confirmPassword,
        onValueChange = onPasswordVerifyChanged,
    )
}

@Composable
private fun EmailField(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChanged: (String) -> Unit
) {
    Row() {
        EchogBasicTextField(
            modifier = modifier.fillMaxWidth(0.8f),
            label = stringResource(R.string.user_info_email),
            placeholder = stringResource(R.string.user_info_placeholder_email),
            value = email,
            onValueChange = onEmailChanged,
        )
        EchogButton(
            modifier = modifier.padding(top = 13.dp, start = 8.dp),
            label = stringResource(R.string.verify),
            onClick = { }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun UserInfoScreenPreview() {
    UserInfoScreen()
}