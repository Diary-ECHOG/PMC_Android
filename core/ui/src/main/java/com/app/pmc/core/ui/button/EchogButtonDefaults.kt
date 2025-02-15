package com.app.pmc.core.ui.button

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.app.pmc.core.ui.theme.Black
import com.app.pmc.core.ui.theme.ButtonDisableBackgroundColor
import com.app.pmc.core.ui.theme.ButtonDisableContentColor
import com.app.pmc.core.ui.theme.ButtonLabel600
import com.app.pmc.core.ui.theme.White

internal class EchogButtonDefaults(
    val iconSize: Int,
    val minWidth: Int,
    val minHeight: Int,
    val textStyle: TextStyle,
    val backgroundColor: Color,
    val disabledBackgroundColor: Color,
    val contentColor: Color,
    val disabledContentColor: Color
) {
    companion object {
        fun fromType(buttonType: ButtonType): EchogButtonDefaults = when (buttonType) {
            ButtonType.Default -> defaultFillButtonDefaults()
        }

        private fun defaultFillButtonDefaults(): EchogButtonDefaults = EchogButtonDefaults(
            iconSize = 22,
            minWidth = 64,
            minHeight = 40,
            textStyle = ButtonLabel600,
            backgroundColor = Black,
            disabledBackgroundColor = ButtonDisableBackgroundColor,
            contentColor = White,
            disabledContentColor = ButtonDisableContentColor
        )
    }
}

enum class ButtonType {
    Default
}
