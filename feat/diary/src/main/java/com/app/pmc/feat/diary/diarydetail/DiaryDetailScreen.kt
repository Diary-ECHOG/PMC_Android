package com.app.pmc.feat.diary.diarydetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.theme.Slate_600
import com.app.pmc.core.ui.topbar.TopBarWithBackButton
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun DiaryDetailScreen(
    viewModel: DiaryDetailViewModel = hiltViewModel(),
    popToBackStack: () -> Unit,
) {
    val state = viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {
            is DiaryDetailSideEffect.PopToBackStack -> popToBackStack()
            else -> {}
        }
    }

    DiaryDetailScreen(
        state = state.value,
        popToBackStack = popToBackStack,
    )
}

@Composable
fun DiaryDetailScreen(
    modifier: Modifier = Modifier,
    state: DiaryDetailScreenState,
    popToBackStack: () -> Unit,
) {
    Box {
        Scaffold(
            topBar = {
                TopBarWithBackButton(
                    modifier = modifier,
                    topBarTitle = state.date ?: "",
                    popToBackStack = popToBackStack,
                    trailingIcon = {
                        IconButton(
                            onClick = { }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_meatball),
                                contentDescription = ""
                            )
                        }
                    }
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
                        Text(
                            color = Slate_600,
                            text = state.title ?: ""
                        )
                    }
                    item {
                        Text(
                            color = Slate_600,
                            text = state.content?: ""
                        )
                    }
                }
            }
        )
    }
}


@Composable
@Preview
private fun DiaryDetailScreenPreview() {
    DiaryDetailScreen(
        state = DiaryDetailScreenState(
            date = "2023-10-01",
            title = "제목",
            content = "내용",
        ),
        popToBackStack = {}
    )
}