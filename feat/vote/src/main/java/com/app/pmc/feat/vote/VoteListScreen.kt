package com.app.pmc.feat.vote

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.pmc.core.ui.topbar.TopBarWithSearchIcon

@Composable
fun VoteListScreen() {
    Scaffold(
        topBar = {
            TopBarWithSearchIcon(
                topBarTitle = "투표",
                placeholder = "Search for vote",
                trailingIcon = {},
                onSearch = {},
                recentSearches = listOf("Vote 1", "Vote 2", "Vote 3")
            )

        },
        content = { innerPadding ->
            Text(modifier = Modifier.padding(innerPadding), text = "Vote List Screen")
        }
    )
}

@Composable
@Preview(showBackground = true)
fun VoteListScreenPreview() {
    VoteListScreen()
}