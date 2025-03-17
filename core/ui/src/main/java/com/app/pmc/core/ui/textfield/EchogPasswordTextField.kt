package com.app.pmc.core.ui.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pmc.core.ui.R

@Composable
fun EchogPasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    label: String? = null,
    placeholder: String? = null,
    supportingTextAlign: TextAlign? = null,
    supportingText: String? = null,
    supportingTextColor: Color? = null,
    isError: Boolean = false,
    maxLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        textStyle = EchogTextFieldDefaults.TextStyles.textStyle,
        maxLines = maxLines,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        interactionSource = interactionSource,
        decorationBox = @Composable { innerTextField ->
            TextFieldDecorationBox(
                modifier = modifier,
                value = value,
                innerTextField = innerTextField,
                interactionSource = interactionSource,
                label = label,
                trailingIcon = {
                    Image(
                        modifier = Modifier.size(16.dp).clickable {
                            passwordVisible = !passwordVisible
                        },
                        painter = painterResource(id =if(passwordVisible) R.drawable.ic_eye else R.drawable.ic_eye_off),
                        contentDescription = null
                    )
                },
                placeholder = placeholder,
                supportingTextAlign = supportingTextAlign,
                supportingText = supportingText,
                supportingTextColor = supportingTextColor,
                enabled = enabled,
                isError = isError
            )
        }
    )
}

@Composable
@Preview
fun EchogPasswordTextFieldPreview() {
    EchogPasswordTextField(
        label = "Label",
        value = "Hello",
        onValueChange = { },
        enabled = true,
        modifier = Modifier
    )
}