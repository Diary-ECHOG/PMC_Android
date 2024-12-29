package com.app.pmc.core.ui.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pmc.ui.theme.TextFieldBorderColor_Focused
import com.app.pmc.ui.theme.TextFieldBorderColor_UnFocused
import com.app.pmc.ui.theme.TextFieldDisablePlaceholderColor
import com.app.pmc.ui.theme.TextFieldEnableBackgroundColor
import com.app.pmc.ui.theme.TextFieldErrorBorderColor
import com.app.pmc.ui.theme.TextFieldPlaceholderColor
import com.app.pmc.ui.theme.Typography
import com.app.pmc.ui.theme.White

@Immutable
internal object EchogTextFieldDefaults {
    val MinWidth = 200.dp
    val AnimationDuration = 150

    val textStyle = Typography.bodyMedium
    val backgroundColor = White
    val disabledBackgroundColor = TextFieldEnableBackgroundColor

    val focusedBorderWidth = 2.dp
    val unfocusedBorderWidth = 1.dp
    val errorBorderWidth = 2.dp

    val focusedBorderColor = TextFieldBorderColor_Focused
    val unfocusedBorderColor = TextFieldBorderColor_UnFocused
    val errorBorderColor = TextFieldErrorBorderColor

    val placeholderTextColor = TextFieldPlaceholderColor
    val disabledPlaceholderTextColor = TextFieldDisablePlaceholderColor

    val supportingTextColor = TextFieldBorderColor_Focused
    val errorSupportingTextColor = TextFieldErrorBorderColor
}

@Composable
fun EchogBasicTextField(
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
    leadingIcon: Painter? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        textStyle = EchogTextFieldDefaults.textStyle,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = @Composable { innerTextField ->
            TextFieldDecorationBox(
                modifier = modifier,
                value = value,
                innerTextField = innerTextField,
                interactionSource = interactionSource,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
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
fun TextFieldDecorationBox(
    modifier: Modifier = Modifier,
    value: String,
    innerTextField: @Composable () -> Unit,
    interactionSource: InteractionSource,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: Painter? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: String? = null,
    supportingTextColor: Color? = null,
    supportingTextAlign: TextAlign? = null,
    enabled: Boolean,
    isError: Boolean
) {
    val isFocused = interactionSource.collectIsFocusedAsState().value

    TextFieldTransitionScope.Transition(
        isFocused = isFocused,
        isError = isError,
        focusedBorderWidth = EchogTextFieldDefaults.focusedBorderWidth,
        unfocusedBorderWidth = EchogTextFieldDefaults.unfocusedBorderWidth,
        errorBorderWidth = EchogTextFieldDefaults.errorBorderWidth,
        focusedBorderColor = EchogTextFieldDefaults.focusedBorderColor,
        unfocusedBorderColor = EchogTextFieldDefaults.unfocusedBorderColor,
        errorBorderColor = EchogTextFieldDefaults.errorBorderColor,
        supportingTextColor = supportingTextColor ?: EchogTextFieldDefaults.supportingTextColor,
        errorTextColor = EchogTextFieldDefaults.errorSupportingTextColor
    ) { border, supportingTextColor ->
        Column(modifier = modifier) {
            Row {
                if (label != null) {
                    Text(
                        text = label,
                        modifier = Modifier.padding(start = 4.dp, bottom = 6.dp),
                        style = EchogTextFieldDefaults.textStyle
                    )
                }
            }

            Row(
                modifier = Modifier
                    .defaultMinSize(minHeight = 40.dp)
                    .border(border = border, shape = RoundedCornerShape(8.dp))
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(
                        color = if (enabled) {
                            EchogTextFieldDefaults.backgroundColor
                        } else {
                            EchogTextFieldDefaults.disabledBackgroundColor
                        }
                    )
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(leadingIcon != null) {
                    Image(
                        painter = leadingIcon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                    )
                }
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart,
                    propagateMinConstraints = true
                ) {
                    if (placeholder != null && value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = EchogTextFieldDefaults.textStyle.copy(
                                color =
                                if (enabled) {
                                    EchogTextFieldDefaults.placeholderTextColor
                                } else {
                                    EchogTextFieldDefaults.disabledPlaceholderTextColor
                                }
                            )
                        )
                    }
                    innerTextField()
                }
                if(trailingIcon != null) {
                    trailingIcon()
                }
            }

            if (supportingText != null) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    textAlign = supportingTextAlign ?: TextAlign.Start,
                    text = supportingText,
                    style = EchogTextFieldDefaults.textStyle.copy(
                        color = supportingTextColor
                    )
                )
            }
        }
    }
}

@Composable
@Preview
fun EchogBasicTextFieldPreview() {
    EchogBasicTextField(
        label = "Label",
        value = "Hello",
        onValueChange = { },
        enabled = true,
        modifier = Modifier
    )
}