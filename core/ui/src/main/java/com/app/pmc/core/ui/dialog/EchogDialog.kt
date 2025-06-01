package com.app.pmc.core.ui.dialog

import android.app.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.button.ButtonType
import com.app.pmc.core.ui.button.EchogButton
import com.app.pmc.core.ui.theme.Slate_600
import com.app.pmc.core.ui.theme.Slate_800

@Composable
fun EchogDialog(
    modifier: Modifier = Modifier,
    title: String = "",
    message: String = "",
    confirmButtonLabel: String = "",
    cancelButtonLabel: String = "",
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = { onCancel() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        ),
    ) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(20.dp)
        ) {
            Text(
                color = Slate_800,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                text = title,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                modifier = modifier.padding(top = 12.dp, bottom = 30.dp),
                color = Slate_600,
                fontWeight = FontWeight.W400,
                fontSize = 15.sp,
                text = message,
                style = MaterialTheme.typography.bodyLarge
            )
            EchogButton(
                modifier = Modifier
                    .fillMaxWidth(),
                label = confirmButtonLabel,
                onClick = onConfirm,
            )
            EchogButton(
                modifier = Modifier
                    .fillMaxWidth(),
                label = cancelButtonLabel,
                onClick = onCancel,
                buttonType = ButtonType.Gray
            )
        }
    }
}

@Composable
@Preview
fun EchogDialogPreview() {
    EchogDialog(
        title = "title",
        message = "message",
        cancelButtonLabel = "ccccc",
        confirmButtonLabel = "confirm",
        onConfirm = {}
    )
}