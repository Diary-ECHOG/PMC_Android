package com.app.pmc.core.ui.dropdown

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.theme.TextFieldBorderColor_UnFocused

@Composable
fun EchogBasicDropdownMenu(
    modifier: Modifier = Modifier,
    selectedOption: String,
    placeholder: String,
    optionList: List<String>,
    onSelectedOption: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .clickable {
                    expanded = !expanded
                }
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(8.dp),
                    color = TextFieldBorderColor_UnFocused
                )
                .padding(14.dp)
        ) {
            Text(
                text = selectedOption.ifEmpty { placeholder }
            )
            Image(
                painter = painterResource(R.drawable.ic_caret),
                contentDescription = ""
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            optionList.forEach { category ->
                DropdownMenuItem(
                    text = {
                        Text(
                            category,
                        )
                    },
                    onClick = {
                        onSelectedOption(category)
                        expanded = !expanded
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun EchogBasicDropdownMenuPreview() {
    EchogBasicDropdownMenu(
        selectedOption = "1",
        placeholder = "",
        optionList = listOf("1", "2", "3", "4"),
        onSelectedOption = {}
    )
}