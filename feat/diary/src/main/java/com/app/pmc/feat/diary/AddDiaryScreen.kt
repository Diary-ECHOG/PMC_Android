package com.app.pmc.feat.diary

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.textfield.EchogNoLineTextField
import com.app.pmc.core.util.LocalDateTimeExtension.toDiaryDate
import com.app.pmc.ui.theme.Blue_500
import com.app.pmc.ui.theme.Slate_600
import org.orbitmvi.orbit.compose.collectAsState
import java.time.LocalDateTime

@Composable
fun AddDiaryScreen(
    viewModel: AddDiaryViewModel = hiltViewModel(),
    popToBackStack: () -> Unit,
    navigateToVote: () -> Unit
) {
    val state = viewModel.collectAsState()

    AddDiaryScreen(
        state = state.value,
        openGallery = {},
        navigateToVote = navigateToVote,
        popToBackStack = popToBackStack,
        onTitleChange = viewModel::onTitleChange,
        onContentChange = viewModel::onContentChange
    )
}

@Composable
fun AddDiaryScreen(
    modifier: Modifier = Modifier,
    state: AddDiaryScreenState,
    openGallery: () -> Unit,
    navigateToVote: () -> Unit,
    popToBackStack: () -> Unit,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit
) {
    Scaffold(
        topBar = {
            Topbar(
                state.date.toDiaryDate(),
                popToBackStack
            )
        },
        bottomBar = {
            BottomBar(
                navigateToVote = navigateToVote,
                openGallery = openGallery
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp)
            ) {
                item {
                    EchogNoLineTextField(
                        value = state.title,
                        textStyle = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.W600
                        ),
                        modifier = Modifier.padding(bottom = 12.dp),
                        onValueChange = onTitleChange,
                        placeholder = stringResource(id = R.string.diary_title_placeholder)
                    )
                }
                item {
                    EchogNoLineTextField(
                        value = state.content,
                        onValueChange = onContentChange,
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400
                        ),
                        placeholder = stringResource(id = R.string.diary_content_placeholder)
                    )
                }
            }
        }
    )
}

@Composable
private fun Topbar(
    date: String,
    popToBackStack: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 48.dp, bottom = 20.dp)
    ) {
        IconButton(
            onClick = popToBackStack,
        ) {
            Text(
                text = stringResource(R.string.cancel)
            )
        }
        Text(
            text = date
        )
        IconButton(
            onClick = popToBackStack,
        ) {
            Text(
                color = Blue_500,
                text = stringResource(R.string.add)
            )
        }
    }
}

@Composable
private fun BottomBar(
    navigateToVote: () -> Unit,
    openGallery:() -> Unit
) {
    Column(modifier = Modifier.imePadding()) {
        Divider(
            color = Slate_600
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottombarButton(
                onClick = openGallery,
                iconRes = R.drawable.ic_image,
                labelRes = R.string.diary_add_image
            )
            BottombarButton(
                onClick = navigateToVote,
                iconRes = R.drawable.ic_rounded_check,
                labelRes = R.string.diary_cast_a_vote
            )
        }
    }
}

@Composable
private fun BottombarButton(
    onClick:() -> Unit,
    iconRes: Int,
    labelRes: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(start = 6.dp),
            text = stringResource(labelRes)
        )
    }
}

@Composable
@Preview
fun AddDiaryScreenPreview() {
    AddDiaryScreen(
        state = AddDiaryScreenState(
            date = LocalDateTime.now()
        ),
        openGallery = {},
        navigateToVote = {},
        onContentChange = {},
        onTitleChange = {},
        popToBackStack = {}
    )
}