package com.app.pmc.core.ui.textfield

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

internal object FilledTextFieldTransitionScope {

    enum class TextFieldState {
        Focused,
        Unfocused,
        Error
    }

    @Composable
    fun Transition(
        isFocused: Boolean,
        isError: Boolean,
        supportingTextColor: Color,
        errorTextColor: Color,
        content: @Composable (supportingTextColor: Color) -> Unit
    ) {
        val transition = updateTransition(
            targetState = when {
                isError -> TextFieldState.Error
                isFocused -> TextFieldState.Focused
                else -> TextFieldState.Unfocused
            },
            label = "EchogTextFieldInputState"
        )

        val textColor by transition.animateColor(
            transitionSpec = { tween(durationMillis = EchogTextFieldDefaults.Dimensions.AnimationDuration) },
            label = "EchogTextFieldBorderColor"
        ) { state ->
            when (state) {
                TextFieldState.Error -> errorTextColor
                else -> supportingTextColor
            }
        }

        content(textColor)
    }
}
