package com.app.pmc.core.ui.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pmc.core.ui.R

@Composable
fun EchogSearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    placeholder: String? = null,
    supportingTextAlign: TextAlign? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    maxLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    EchogFilledTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        isError = isError,
        maxLines = maxLines,
        interactionSource = interactionSource,
        trailingIcon = {
            if (value.isNotEmpty()) Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onValueChange("") },
                painter = painterResource(id = R.drawable.ic_x_circle_solid),
                contentDescription = null
            )
        },
        placeholder = placeholder,
        supportingTextAlign = supportingTextAlign,
        supportingText = supportingText,
    )
}

@Composable
@Preview
fun EchogSearchTextFieldPreview() {
    EchogSearchTextField(
        value = "Hello",
        onValueChange = { },
        enabled = true,
        modifier = Modifier
    )
}