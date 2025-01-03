package com.app.pmc.core.ui.textfield

import android.util.Log
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EchogFilledTextField(
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
        textStyle = EchogTextFieldDefaults.TextStyles.textStyle,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = @Composable { innerTextField ->
            FilledTextFieldDecorationBox(
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
                enabled = enabled,
                isError = isError
            )
        }
    )
}

@Composable
fun FilledTextFieldDecorationBox(
    modifier: Modifier = Modifier,
    value: String,
    innerTextField: @Composable () -> Unit,
    interactionSource: InteractionSource,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: Painter? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: String? = null,
    supportingTextAlign: TextAlign? = null,
    enabled: Boolean,
    isError: Boolean,
) {
    val isFocused = interactionSource.collectIsFocusedAsState().value

    FilledTextFieldTransitionScope.Transition(
        isFocused = isFocused,
        isError = isError,
        supportingTextColor = FilledTextFieldDefaults.Colors.supportingTextColor,
        errorTextColor = FilledTextFieldDefaults.Colors.errorSupportingTextColor
    ) { _ ->
        Column(modifier = modifier) {
            Row {
                if (label != null) {
                    Text(
                        text = label,
                        modifier = Modifier.padding(start = 4.dp, bottom = 6.dp),
                        style = FilledTextFieldDefaults.TextStyles.textStyle
                    )
                }
            }

            Row(
                modifier = Modifier
                    .defaultMinSize(minHeight = 40.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(
                        color = if (enabled) {
                            FilledTextFieldDefaults.Colors.backgroundColor
                        } else {
                            FilledTextFieldDefaults.Colors.disabledBackgroundColor
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
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart,
                    propagateMinConstraints = true
                ) {
                    if (placeholder != null && value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = FilledTextFieldDefaults.TextStyles.textStyle.copy(
                                color =
                                if (enabled) {
                                    FilledTextFieldDefaults.Colors.placeholderTextColor
                                } else {
                                    FilledTextFieldDefaults.Colors.disabledPlaceholderTextColor
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
                    style = FilledTextFieldDefaults.TextStyles.textStyle.copy(
                        color = FilledTextFieldDefaults.Colors.supportingTextColor
                    )
                )
            }
        }
    }
}

@Composable
@Preview
fun EchogFilledTextFieldPreview() {
    EchogBasicTextField(
        label = "Label",
        value = "Hello",
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