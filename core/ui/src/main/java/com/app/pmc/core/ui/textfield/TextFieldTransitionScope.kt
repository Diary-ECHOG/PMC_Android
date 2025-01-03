package com.app.pmc.core.ui.textfield

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

internal object TextFieldTransitionScope {

    enum class TextFieldState {
        Focused,
        Unfocused,
        Error
    }

    @Composable
    fun Transition(
        isFocused: Boolean,
        isError: Boolean,
        focusedBorderWidth: Dp,
        unfocusedBorderWidth: Dp,
        errorBorderWidth: Dp,
        focusedBorderColor: Color,
        unfocusedBorderColor: Color,
        errorBorderColor: Color,
        supportingTextColor: Color,
        errorTextColor: Color,
        content: @Composable (border: BorderStroke, supportingTextColor: Color) -> Unit
    ) {
        val transition = updateTransition(
            targetState = when {
                isError -> TextFieldState.Error
                isFocused -> TextFieldState.Focused
                else -> TextFieldState.Unfocused
            },
            label = "EchogTextFieldInputState"
        )

        val borderWidth by transition.animateDp(
            transitionSpec = { tween(durationMillis = EchogTextFieldDefaults.Dimensions.AnimationDuration) },
            label = "EchogTextFieldBorderWidth"
        ) { state ->
            when (state) {
                TextFieldState.Focused -> focusedBorderWidth
                TextFieldState.Unfocused -> unfocusedBorderWidth
                TextFieldState.Error -> errorBorderWidth
            }
        }

        val borderColor by transition.animateColor(
            transitionSpec = { tween(durationMillis = EchogTextFieldDefaults.Dimensions.AnimationDuration) },
            label = "EchogTextFieldBorderColor"
        ) { state ->
            when (state) {
                TextFieldState.Focused -> focusedBorderColor
                TextFieldState.Unfocused -> unfocusedBorderColor
                TextFieldState.Error -> errorBorderColor
            }
        }

        val textColor by transition.animateColor(
            transitionSpec = { tween(durationMillis = EchogTextFieldDefaults.Dimensions.AnimationDuration) },
            label = "EchogTextFieldBorderColor"
        ) { state ->
            when (state) {
                TextFieldState.Error -> errorTextColor
                else -> supportingTextColor
            }
        }

        content(BorderStroke(width = borderWidth, color = borderColor), textColor)
    }
}
