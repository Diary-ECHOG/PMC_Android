package com.app.pmc.feat.mypage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pmc.core.ui.topbar.TopBarWithBackButton
import com.app.pmc.core.ui.R.string
import com.app.pmc.core.ui.theme.Gray_100
import com.app.pmc.core.ui.theme.Slate_400
import com.app.pmc.core.ui.theme.Slate_600
import com.app.pmc.core.ui.theme.TextSmall

@Composable
fun MyVoteListScreen(
    modifier: Modifier = Modifier,
    popToBackStack: () -> Unit = {}
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    LazyColumn {
        item {
            TopBarWithBackButton(
                topBarTitle = stringResource(id = string.my_vote_list),
                popToBackStack = popToBackStack,
            )
        }
        item {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.padding(top = 8.dp),
                contentColor = MaterialTheme.colorScheme.onSurface,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = MaterialTheme.colorScheme.onSurface,
                        height = 1.dp
                    )
                }
            ) {
                Column(modifier = Modifier
                    .padding(20.dp)
                    .clickable {
                        selectedTabIndex = 0
                    }, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(id = string.proceeding_vote))
                }
                Column(modifier = Modifier
                    .padding(20.dp)
                    .clickable {
                        selectedTabIndex = 1
                    }, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(id = string.completed_vote))
                }
            }
        }
        items(10) {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                VoteItem(
                    title = "투표 제목",
                    description = "투표 설명",
                    voteCount = 100,
                    date = "11월 21일 목요일"
                )
            }
        }
    }
}

@Composable
private fun VoteItem(
    modifier: Modifier = Modifier,
    title: String = "",
    description: String = "",
    date: String = "",
    voteCount: Int = 0
) {
    Column() {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = title, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.W600, fontSize = 15.sp)
            Text(
                text = description,
                modifier = modifier.padding(top = 4.dp, bottom = 12.dp),
                color = Slate_600,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = TextSmall.fontSize
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = date, color = Slate_400, fontSize = 13.sp, fontWeight = FontWeight.W500)
                Text(
                    text = voteCount.toString(),
                    color = Slate_400,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }
        Divider(
            color = Gray_100,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MyVoteListScreenPreview() {
    MyVoteListScreen()
}