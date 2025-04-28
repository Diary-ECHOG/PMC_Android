package com.app.pmc.feat.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.pmc.core.model.Diary
import com.app.pmc.core.ui.R.drawable
import com.app.pmc.core.ui.R.string
import com.app.pmc.core.ui.card.EchogDefaultCard
import com.app.pmc.core.ui.surface.GradientSurface
import com.app.pmc.core.ui.theme.NormalButtonBorderColor
import com.app.pmc.core.ui.theme.NormalButtonContentColor
import com.app.pmc.core.ui.theme.White
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    addDiary: () -> Unit = {},
    navigateToVoteList: () -> Unit = {},
    navigateToMyPage: () -> Unit = {}
) {
    val state = viewModel.collectAsState()

    HomeScreen(
        state = state.value,
        addDiary = addDiary,
        navigateToVoteList = navigateToVoteList,
        navigateToMyPage = navigateToMyPage
    )
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    addDiary:() -> Unit,
    navigateToMyPage: () -> Unit,
    navigateToVoteList: () -> Unit
) {
    val gradientList =
        listOf(Color.Transparent, MaterialTheme.colorScheme.background.copy(alpha = 0.95f))

    GradientSurface(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Top(
                        navigateToVoteList = navigateToVoteList,
                        navigateToMyPage = navigateToMyPage
                    )
                }
                item {
                    Text(
                        text = stringResource(id = string.yesterday),
                        fontWeight = FontWeight.W700,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
                items(state.diaryList.size, key = { index -> state.diaryList[index].id }) { _ ->
                    EchogDefaultCard(
                        title = "Title",
                        description = "Description",
                        subDescription = {
                            Text(
                                text = "2021.10.10"
                            )
                        }
                    )
                }
                item {
                    Text(
                        text = state.month,
                        fontWeight = FontWeight.W700,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
                items(
                    state.monthlyDiaryList.size,
                    key = { index -> state.monthlyDiaryList[index].id }) { _ ->
                    EchogDefaultCard(
                        modifier = Modifier.padding(top = 10.dp),
                        title = "Title",
                        description = "Description",
                        subDescription = {
                            Text(
                                text = "2021.10.10"
                            )
                        }
                    )

                }
            }
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomStart)
            ) {
                val gradientBrush = Brush.verticalGradient(
                    colors = gradientList,
                    startY = 0f,
                    endY = size.height
                )
                drawRect(brush = gradientBrush)
            }
            FloatingActionButton(
                onClick = addDiary,
                shape = RoundedCornerShape(50),
                containerColor = White,
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                )
            }
        }
    }
}

@Composable
private fun Top(
    modifier: Modifier = Modifier,
    navigateToVoteList: () -> Unit = {},
    navigateToMyPage: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 62.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier.weight(1f),
            alignment = Alignment.CenterStart,
            painter = painterResource(id = drawable.ic_app_name),
            contentDescription = "Logo",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surfaceTint)
        )
        VoteViewButton(
            onClick = navigateToVoteList
        )
        IconButton(
            onClick = navigateToMyPage,
        ) {
            Image(
                painter = painterResource(id = drawable.ic_meatball),
                contentDescription = "meatball"
            )
        }
    }
}

@Composable
private fun VoteViewButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, NormalButtonBorderColor),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = drawable.ic_rounded_check),
                contentDescription = "check"
            )
            Text(
                fontWeight = FontWeight.W700,
                color = NormalButtonContentColor,
                text = stringResource(id = string.show_my_vote),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    HomeScreen(
        navigateToVoteList = {},
        navigateToMyPage = {},
        addDiary = {},
        state = HomeUiState(
            month = "10월",
            diaryList = listOf(
                Diary(
                    id = "111",
                    title = "Title",
                    content = "Content",
                    date = "2021.10.10"
                ),
                Diary(
                    id = "222",
                    title = "Title",
                    content = "Content",
                    date = "2021.10.10"
                ),
                Diary(
                    id = "333",
                    title = "Title",
                    content = "Content",
                    date = "2021.10.10"
                )
            ),
            monthlyDiaryList = listOf(
                Diary(
                    id = "444",
                    title = "Title",
                    content = "Content",
                    date = "2021.10.10"
                ),
                Diary(
                    id = "555",
                    title = "Title",
                    content = "Content",
                    date = "2021.10.10"
                ),
                Diary(
                    id = "666",
                    title = "Title",
                    content = "Content",
                    date = "2021.10.10"
                )
            )
        )
    )
}