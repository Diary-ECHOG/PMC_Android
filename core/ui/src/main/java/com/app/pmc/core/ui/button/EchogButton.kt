package com.app.pmc.core.ui.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.app.pmc.core.ui.R

@Composable
fun EchogButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingPainter: Painter? = null,
    trailingPainter: Painter? = null,
    buttonType: ButtonType = ButtonType.Default,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    label: String = stringResource(R.string.complete),
    labelStyle: TextStyle = TextStyle.Default
) {
    val buttonDefaults = EchogButtonDefaults.fromType(buttonType)

    val containerColor = when {
        !enabled -> buttonDefaults.disabledBackgroundColor
        else -> buttonDefaults.backgroundColor
    }

    val contentColor = when {
        !enabled -> buttonDefaults.disabledContentColor
        else -> buttonDefaults.contentColor
    }

    Surface(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(minWidth = buttonDefaults.minWidth.dp)
            .semantics { role = Role.Button },
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        color = containerColor,
        contentColor = contentColor,
    ) {
        Row(
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment
        ) {
            if (leadingPainter != null) {
                Image(
                    painter = leadingPainter,
                    contentDescription = null,
                    modifier = Modifier.size(buttonDefaults.iconSize.dp),
                    colorFilter = ColorFilter.tint(contentColor)
                )
            }
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = label,
                color = contentColor,
                style = labelStyle
            )

            if (trailingPainter != null) {
                Image(
                    painter = trailingPainter,
                    contentDescription = null,
                    modifier = Modifier.size(buttonDefaults.iconSize.dp),
                    colorFilter = ColorFilter.tint(contentColor)
                )
            }
        }
    }
}
