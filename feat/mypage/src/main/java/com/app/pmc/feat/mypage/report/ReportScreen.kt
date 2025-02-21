package com.app.pmc.feat.mypage.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.theme.Blue_10
import com.app.pmc.core.ui.theme.Blue_800
import com.app.pmc.core.ui.theme.Gray_100
import com.app.pmc.core.ui.theme.Slate_100
import com.app.pmc.core.ui.theme.Slate_800
import com.app.pmc.core.ui.topbar.TopBarWithBackButton
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ReportScreen(
    viewModel: ReportViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val state by viewModel.collectAsState()

    ReportScreen(
        state = state,
        popBackStack = popBackStack
    )
}

@Composable
private fun ReportScreen(
    modifier: Modifier = Modifier,
    popBackStack: () -> Unit,
    state: ReportState
) {
    Scaffold(
        topBar = {
            TopBarWithBackButton(
                topBarTitle = stringResource(R.string.report_list),
                popToBackStack = popBackStack,
            )
        },
        content = { innerPadding ->
            LazyColumn(modifier = modifier.padding(innerPadding)) {
                item {
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp)
                            .background(color = Blue_10)
                            .padding(14.dp),
                        color = Blue_800,
                        text = stringResource(R.string.report_notification)
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Gray_100))
                }
                items(state.reportList.size) { index ->
                    ReportItem(
                        report = state.reportList[index]
                    )
                    Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Gray_100))
                }
            }
        }
    )
}

@Composable
private fun ReportItem(
    modifier: Modifier = Modifier,
    report: Report
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(18.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                modifier = modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Slate_100)
                    .padding(6.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                text = report.state,
                color = Slate_800
            )
            Text(
                fontSize = 15.sp,
                fontWeight = FontWeight.W600,
                text = report.title,
                color = Slate_800
            )
        }
        IconButton(
            onClick = { }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = ""
            )
        }
    }
}

@Composable
@Preview
fun ReportScreenPreview() {
    ReportScreen(
        popBackStack = {},
        state = ReportState(
            reportList = listOf(
                Report(
                    state = "처리 대기",
                    date = "2021.10.10",
                    title = "title",
                ),
                Report(
                    state = "처리 중",
                    date = "2021.10.10",
                    title = "title",
                ),
                Report(
                    state = "처리 중",
                    date = "2021.10.10",
                    title = "title",
                ),
                Report(
                    state = "처리 완료",
                    date = "2021.10.10",
                    title = "title",
                )
            )
        )
    )
}