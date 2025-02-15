package com.app.pmc.feat.vote

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.card.EchogDefaultCard
import com.app.pmc.core.ui.dropdown.EchogBasicDropdownMenu
import com.app.pmc.core.ui.surface.GradientSurface
import com.app.pmc.core.ui.theme.NormalButtonBorderColor
import com.app.pmc.core.ui.theme.Slate_400
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun VoteListScreen(
    viewModel: VoteListViewModel = hiltViewModel(),
) {
    val state = viewModel.collectAsState()
    VoteListScreen(
        state = state.value
    )
}

@Composable
internal fun VoteListScreen(
    modifier: Modifier = Modifier,
    state: VoteListUiState,
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
                        navigateToDiaryList = {}
                    )
                }
                item {
                    EchogBasicDropdownMenu(
                        modifier = modifier,
                        selectedOption = state.selectedCategory,
                        placeholder = stringResource(R.string.show_all_category_vote),
                        optionList = state.categoryList,
                        onSelectedOption = {}
                    )
                }
                item {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W600,
                            text = stringResource(id = R.string.vote_list_count, state.voteList.size),
                        )
                        Row(modifier = modifier.padding(4.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_filter),
                                contentDescription = "search"
                            )
                            Text(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.W600,
                                text = stringResource(id = R.string.sort_by_recent),
                            )
                        }
                    }
                }
                items(state.voteList.size, key = { index -> state.voteList[index].id }) { index ->
                    EchogDefaultCard(
                        title = state.voteList[index].title,
                        description = state.voteList[index].description,
                        subDescription = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = modifier.fillMaxWidth()
                            ) {
                                Text(
                                    color = Slate_400,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.W500,
                                    text = "${state.voteList[index].nickname} · ${state.voteList[index].date}"
                                )
                                Text(
                                    color = Slate_400,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.W500,
                                    text = stringResource(
                                        R.string.vote_count,
                                        state.voteList[index].voteCount
                                    )
                                )
                            }
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
        }
    }
}


@Composable
private fun Top(
    modifier: Modifier = Modifier,
    navigateToDiaryList: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 62.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = modifier.weight(1f),
            fontSize = 22.sp,
            fontWeight = FontWeight.W600,
            text = stringResource(id = R.string.vote_list_title),
        )
        DiaryViewButton(
            onClick = navigateToDiaryList
        )
        IconButton(
            onClick = {},
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "search"
            )
        }
    }
}


@Composable
private fun DiaryViewButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
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
                painter = painterResource(id = R.drawable.ic_book),
                contentDescription = "book"
            )
            Text(
                fontWeight = FontWeight.W700,
                text = stringResource(id = R.string.show_my_diary),
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun VoteListScreenPreview() {
    VoteListScreen(
        state = VoteListUiState(
            voteList = listOf(
                Vote(
                    id = "1",
                    title = "Title",
                    description = "Description",
                    voteCount = 10,
                    nickname = "익명 18",
                    date = "Date",
                    isVoted = false,
                ),
                Vote(
                    id = "4",
                    title = "Title",
                    description = "Description",
                    voteCount = 10,
                    nickname = "익명 18",
                    date = "Date",
                    isVoted = false,
                ),
                Vote(
                    id = "2",
                    title = "Title",
                    description = "Description",
                    voteCount = 10,
                    nickname = "익명 18",
                    date = "Date",
                    isVoted = false,
                ),
                Vote(
                    id = "3",
                    title = "Title",
                    description = "Description",
                    voteCount = 10,
                    nickname = "익명 18",
                    date = "Date",
                    isVoted = false,
                )
            ),
            categoryList = listOf("1", "2", "3"),
            selectedCategory = "1",
        )
    )
}