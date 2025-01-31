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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EchogNoLineTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    placeholder: String? = null,
    maxLines: Int = 1,
    leadingIcon: Painter? = null,
    textStyle: TextStyle = EchogTextFieldDefaults.TextStyles.textStyle,
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
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        decorationBox = @Composable { innerTextField ->
            NoLineTextFieldDecorationBox(
                modifier = modifier,
                value = value,
                innerTextField = innerTextField,
                leadingIcon = leadingIcon,
                textStyle = textStyle,
                trailingIcon = trailingIcon,
                placeholder = placeholder,
                enabled = enabled,
            )
        }
    )
}

@Composable
fun NoLineTextFieldDecorationBox(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle,
    innerTextField: @Composable () -> Unit,
    placeholder: String? = null,
    leadingIcon: Painter? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean,
) {
    Column(modifier = modifier) {
        Row(
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
                        style = textStyle.copy(
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
    }
}

@Composable
@Preview
fun EchogNoLineTextFieldPreview() {
    EchogNoLineTextField(
        value = "Hello",
        onValueChange = { },
        enabled = true,
        modifier = Modifier,
        placeholder = "Placeholder",
        maxLines = 1,
        leadingIcon = null,
        trailingIcon = null,
        visualTransformation = VisualTransformation.None,
    )
}