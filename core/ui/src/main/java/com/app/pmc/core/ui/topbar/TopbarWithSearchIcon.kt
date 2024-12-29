package com.app.pmc.core.ui.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.textfield.EchogBasicTextField
import com.app.pmc.ui.theme.Gray_400
import com.app.pmc.ui.theme.Gray_600

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithSearchIcon(
    topBarTitle: String = "",
    placeholder: String = stringResource(R.string.search_place_holder),
    trailingIcon: @Composable (() -> Unit)? = null,
    query: String = "",
    recentSearches: List<String> = emptyList(),
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var active = remember { mutableStateOf(true) }
    var searchBarVisible = remember { mutableStateOf(false) }
    var innerQuery = remember { mutableStateOf(query) }

    Box(modifier = modifier) {
        if (searchBarVisible.value) {
            //최근 검색어
            LazyColumn {
                item {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        EchogBasicTextField(
                            modifier = Modifier.weight(0.9f),
                            trailingIcon = {
                                Image(
                                    modifier = Modifier.clickable {
                                        innerQuery.value = ""
                                    },
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_x_circle_solid),
                                    contentDescription = ""
                                )
                            },
                            placeholder = stringResource(R.string.search_place_holder),
                            value = innerQuery.value,
                            onValueChange = { innerQuery.value = it },
                        )
                        Text(
                            text = stringResource(R.string.cancel),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    searchBarVisible.value = false
                                }
                                .weight(0.2f)
                        )
                    }
                }
                item {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = stringResource(R.string.recent_search_keyword),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Gray_400
                        )
                        Text(
                            text = stringResource(R.string.delete_all),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Gray_600
                        )
                    }
                }
                items(recentSearches.size) {
                    RecentItem(
                        onSearch = { onSearch(recentSearches[it]) },
                        recentSearch = recentSearches[it]
                    )
                }
            }
        } else {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = topBarTitle,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W600,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                IconButton(
                    onClick = { searchBarVisible.value = true },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun RecentItem(modifier: Modifier = Modifier, onSearch: () -> Unit, recentSearch: String) {
    Row(
        modifier = modifier
            .clickable { onSearch() }
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_search_with_circle),
            contentDescription = ""
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            text = recentSearch,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_x_circle_solid),
            contentDescription = ""
        )
    }
}

@Composable
@Preview
fun TopBarWithSearchIconPreview() {
    TopBarWithSearchIcon(
        topBarTitle = stringResource(R.string.vote_list_title),
        onSearch = {},
    )
}