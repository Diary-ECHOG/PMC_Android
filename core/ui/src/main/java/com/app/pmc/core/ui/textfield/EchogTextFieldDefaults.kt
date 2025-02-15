package com.app.pmc.core.ui.textfield

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp
import com.app.pmc.core.ui.theme.Gray_400
import com.app.pmc.core.ui.theme.Slate_25
import com.app.pmc.core.ui.theme.TextFieldBorderColor_Focused
import com.app.pmc.core.ui.theme.TextFieldBorderColor_UnFocused
import com.app.pmc.core.ui.theme.TextFieldDisablePlaceholderColor
import com.app.pmc.core.ui.theme.TextFieldEnableBackgroundColor
import com.app.pmc.core.ui.theme.TextFieldErrorBorderColor
import com.app.pmc.core.ui.theme.TextFieldPlaceholderColor
import com.app.pmc.core.ui.theme.Typography
import com.app.pmc.core.ui.theme.White

@Immutable
object EchogTextFieldDefaults {
    val Dimensions = DimensionsObject
    val Colors = ColorsObject
    val TextStyles = TextStylesObject

    object DimensionsObject {
        val MinWidth = 200.dp
        val AnimationDuration = 150
        val focusedBorderWidth = 2.dp
        val unfocusedBorderWidth = 1.dp
        val errorBorderWidth = 2.dp
    }

    object ColorsObject {
        val backgroundColor = White
        val disabledBackgroundColor = TextFieldEnableBackgroundColor

        val focusedBorderColor = TextFieldBorderColor_Focused
        val unfocusedBorderColor = TextFieldBorderColor_UnFocused
        val errorBorderColor = TextFieldErrorBorderColor

        val placeholderTextColor = TextFieldPlaceholderColor
        val disabledPlaceholderTextColor = TextFieldDisablePlaceholderColor

        val supportingTextColor = TextFieldBorderColor_Focused
        val errorSupportingTextColor = TextFieldErrorBorderColor
    }

    object TextStylesObject {
        val textStyle = Typography.bodyMedium
    }
}

@Immutable
object FilledTextFieldDefaults {
    val Colors = FilledTextFieldDefaultsTokens
    val TextStyles = TextStylesObject

    object FilledTextFieldDefaultsTokens {
        val backgroundColor = Slate_25
        val disabledBackgroundColor = Slate_25

        val placeholderTextColor = Gray_400
        val disabledPlaceholderTextColor = Gray_400

        val supportingTextColor = Gray_400
        val errorSupportingTextColor = Gray_400
    }

    object TextStylesObject {
        val textStyle = Typography.bodyMedium
    }
}