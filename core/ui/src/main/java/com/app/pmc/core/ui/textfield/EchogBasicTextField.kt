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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
    isError: Boolean = false,
    maxLines: Int = 1,
    leadingIcon: Painter? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    defaultMinHeight: Dp? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    decorationBox: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        textStyle = EchogTextFieldDefaults.TextStyles.textStyle,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = @Composable { innerTextField ->
            TextFieldDecorationBox(
                modifier = modifier,
                value = value,
                defaultMinHeight = defaultMinHeight,
                innerTextField = innerTextField,
                interactionSource = interactionSource,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                placeholder = placeholder,
                supportingTextAlign = supportingTextAlign,
                supportingText = supportingText,
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
    defaultMinHeight: Dp? = null,
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
    isError: Boolean,
) {
    val isFocused = interactionSource.collectIsFocusedAsState().value

    TextFieldTransitionScope.Transition(
        isFocused = isFocused,
        isError = isError,
        focusedBorderWidth = EchogTextFieldDefaults.Dimensions.focusedBorderWidth,
        unfocusedBorderWidth = EchogTextFieldDefaults.Dimensions.unfocusedBorderWidth,
        errorBorderWidth = EchogTextFieldDefaults.Dimensions.errorBorderWidth,
        focusedBorderColor = EchogTextFieldDefaults.Colors.focusedBorderColor,
        unfocusedBorderColor = EchogTextFieldDefaults.Colors.unfocusedBorderColor,
        errorBorderColor = EchogTextFieldDefaults.Colors.errorBorderColor,
        supportingTextColor = supportingTextColor
            ?: EchogTextFieldDefaults.Colors.supportingTextColor,
        errorTextColor = EchogTextFieldDefaults.Colors.errorSupportingTextColor
    ) { border, _ ->
        Column(modifier = modifier) {
            Row {
                if (label != null) {
                    Text(
                        text = label,
                        modifier = Modifier.padding(start = 4.dp, bottom = 6.dp),
                        style = EchogTextFieldDefaults.TextStyles.textStyle
                    )
                }
            }

            Row(
                modifier = Modifier
                    .border(border = border, shape = RoundedCornerShape(8.dp))
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(
                        color = if (enabled) {
                            EchogTextFieldDefaults.Colors.backgroundColor
                        } else {
                            EchogTextFieldDefaults.Colors.disabledBackgroundColor
                        }
                    )
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    Image(
                        painter = leadingIcon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .defaultMinSize(
                            minHeight = defaultMinHeight ?: TextFieldDefaults.MinHeight,
                        ),
                    contentAlignment = Alignment.CenterStart,
                    propagateMinConstraints = true
                ) {
                    if (placeholder != null && value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = EchogTextFieldDefaults.TextStyles.textStyle.copy(
                                color =
                                if (enabled) {
                                    EchogTextFieldDefaults.Colors.placeholderTextColor
                                } else {
                                    EchogTextFieldDefaults.Colors.disabledPlaceholderTextColor
                                }
                            )
                        )
                    }
                    innerTextField()
                }
                if (trailingIcon != null) {
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
                    style = EchogTextFieldDefaults.TextStyles.textStyle.copy(
                        color = EchogTextFieldDefaults.Colors.supportingTextColor
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
        value = "HelloHelloHelloHelloHello\nHelloHelloHello",
        onValueChange = { },
        enabled = true,
        modifier = Modifier,
        placeholder = "Placeholder",
        supportingText = "Supporting Text",
        isError = false,
        maxLines = 1,
        leadingIcon = null,
        trailingIcon = null,
        visualTransformation = VisualTransformation.None,
        decorationBox = { }
    )
}