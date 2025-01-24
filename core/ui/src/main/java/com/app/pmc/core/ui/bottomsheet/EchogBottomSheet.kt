package com.app.pmc.core.ui.bottomsheet

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EchogBottomSheet(
    content: @Composable () -> Unit = {},
    bottomSheetDefault: BottomSheetDefaults = BottomSheetDefaults,
    showBottomSheet: Boolean = false,
    saveShowBottomSheetState: (Boolean) -> Unit = {},
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    val scope = rememberCoroutineScope()

    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            dragHandle = {
                bottomSheetDefault.DragHandle()
            },
            shape = BottomSheetDefaults.ExpandedShape,
            containerColor = BottomSheetDefaults.ContainerColor,
            contentColor = contentColorFor(BottomSheetDefaults.ContainerColor),
            tonalElevation = BottomSheetDefaults.Elevation,
            scrimColor = BottomSheetDefaults.ScrimColor,
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                    saveShowBottomSheetState(false)
                }
            }
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun EchogBottomSheetPreview() {
    EchogBottomSheet(
        sheetState = rememberModalBottomSheetState(),
        showBottomSheet = true
    )
}