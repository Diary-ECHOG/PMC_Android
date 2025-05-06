package com.app.pmc.feat.diary.diarydetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.pmc.core.ui.R
import com.app.pmc.core.ui.bottomsheet.EchogBottomSheet
import com.app.pmc.core.ui.button.EchogButton
import com.app.pmc.core.ui.textfield.EchogBasicTextField
import com.app.pmc.core.ui.textfield.EchogNoLineTextField
import com.app.pmc.core.util.LocalDateTimeExtension.toDiaryDate
import com.app.pmc.core.ui.theme.ButtonLabel500
import com.app.pmc.core.ui.theme.ButtonLabel600
import com.app.pmc.core.ui.theme.Slate_100
import com.app.pmc.core.ui.theme.Slate_25
import com.app.pmc.core.ui.theme.Slate_600
import com.app.pmc.core.ui.theme.TextFieldBorderColor_UnFocused
import com.app.pmc.core.ui.topbar.TopBarWithBackButton
import com.app.pmc.feat.diary.BottomSheetState
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDateTime

@Composable
fun DiaryDetailScreen(
    viewModel: DiaryDetailViewModel = hiltViewModel(),
    popToBackStack: () -> Unit,
    navigateToVote: () -> Unit
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
        openGallery = {},
        onBottomSheetTitleChanged = viewModel::onBottomSheetTitleChanged,
        navigateToVote = navigateToVote,
        popToBackStack = popToBackStack,
        toggleDuplicateSelection = viewModel::toggleDuplicateSelection,
        addVote = viewModel::addVote,
        addOption = viewModel::addOption,
        onSelectedCategory = viewModel::onBottomSheetCategoryChanged,
        onBottomSheetContentChanged = viewModel::onBottomSheetContentChanged,
    )
}

@Composable
fun DiaryDetailScreen(
    modifier: Modifier = Modifier,
    state: DiaryDetailScreenState,
    addVote: () -> Unit,
    openGallery: () -> Unit,
    navigateToVote: () -> Unit,
    toggleDuplicateSelection: (Boolean) -> Unit,
    onBottomSheetContentChanged: (String) -> Unit,
    onBottomSheetTitleChanged: (String) -> Unit,
    popToBackStack: () -> Unit,
    addOption: () -> Unit,
    onSelectedCategory: (String) -> Unit,
) {
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    Box {
        Scaffold(
            topBar = {
                TopBarWithBackButton(
                    modifier = modifier,
                    topBarTitle = state.date.toDiaryDate(),
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
            bottomBar = {
                BottomBar(
                    navigateToVote = {
                        isBottomSheetOpen = !isBottomSheetOpen
                    },
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
                        Text(
                            color = Slate_600,
                            text = state.title
                        )
                    }
                    item {
                        Text(
                            color = Slate_600,
                            text = state.content
                        )
                    }
                }
            }
        )
        if (isBottomSheetOpen) AddVoteBottomSheet(
            bottomSheetState = state.bottomSheetState,
            toggleDuplicateSelection = toggleDuplicateSelection,
            onTitleChange = onBottomSheetTitleChanged,
            onContentChange = onBottomSheetContentChanged,
            onDismiss = { isBottomSheetOpen = false },
            addVote = addVote,
            addOption = addOption,
            onSelectedCategory = onSelectedCategory,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddVoteBottomSheet(
    bottomSheetState: BottomSheetState,
    toggleDuplicateSelection: (Boolean) -> Unit,
    onSelectedCategory: (String) -> Unit,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    addVote: () -> Unit,
    addOption: () -> Unit,
    onDismiss: () -> Unit
) {
    val canAddOption = bottomSheetState.option.size <= 3

    EchogBottomSheet(
        onDismiss = onDismiss,
        {
            LazyColumn(
                modifier = Modifier.padding(20.dp)
            ) {
                item {
                    EchogNoLineTextField(
                        value = bottomSheetState.title,
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
                        value = bottomSheetState.content,
                        onValueChange = onContentChange,
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400
                        ),
                        placeholder = stringResource(id = R.string.diary_content_placeholder)
                    )
                }
                item {
                    SelectCategoryDropdown(
                        selectedCategory = bottomSheetState.selectedCategory,
                        onSelectedCategory = onSelectedCategory,
                        categoryList = bottomSheetState.categoryList
                    )
                }
                item {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 368.dp)
                            .background(Slate_25)
                            .padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            color = Slate_600,
                            text = stringResource(R.string.diary_vote_option)
                        )
                        bottomSheetState.option.take(2).forEach { _ ->
                            EchogBasicTextField(
                                placeholder = stringResource(R.string.diary_vote_option_placeholder),
                                value = "",
                                onValueChange = {},
                            )
                        }
                        if (bottomSheetState.option.size > 2) bottomSheetState.option.dropLast(2)
                            .forEach { _ ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    EchogBasicTextField(
                                        modifier = Modifier.fillMaxWidth(0.9f),
                                        placeholder = stringResource(R.string.diary_vote_option_placeholder),
                                        value = "",
                                        onValueChange = {},
                                    )
                                    IconButton(
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = {},
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_delete),
                                            contentDescription = "meatball"
                                        )
                                    }
                                }
                            }
                        EchogButton(
                            modifier = Modifier.fillMaxWidth(),
                            label = stringResource(R.string.diary_add_vote_option),
                            labelStyle = ButtonLabel600,
                            enabled = canAddOption,
                            onClick = addOption
                        )
                        DuplicateSelectionCheckBox(
                            toggleDuplicateSelection = toggleDuplicateSelection,
                            allowDuplicateSelection = bottomSheetState.allowDuplicateSelection
                        )
                    }
                }
                item {
                    EchogButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        label = stringResource(R.string.diary_create_vote),
                        labelStyle = ButtonLabel500,
                        enabled = bottomSheetState.title.isNotEmpty(),
                        onClick = addVote
                    )
                }
            }
        }
    )
}

@Composable
private fun DuplicateSelectionCheckBox(
    toggleDuplicateSelection: (Boolean) -> Unit,
    allowDuplicateSelection: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            toggleDuplicateSelection(!allowDuplicateSelection)
        }
    ) {
        Checkbox(
            checked = allowDuplicateSelection,
            onCheckedChange = toggleDuplicateSelection
        )
        Text(
            text = stringResource(R.string.diary_allow_duplicate_selection)
        )
    }
}

@Composable
private fun SelectCategoryDropdown(
    selectedCategory: String,
    categoryList: List<String>,
    onSelectedCategory: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(vertical = 14.dp)
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
                text = selectedCategory.ifEmpty {
                    stringResource(R.string.select_category)
                }
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
            categoryList.forEach { category ->
                DropdownMenuItem(
                    text = {
                        Text(
                            category,
                        )
                    },
                    onClick = {
                        onSelectedCategory(category)
                        expanded = !expanded
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navigateToVote: () -> Unit,
    openGallery: () -> Unit
) {
    Column(modifier = Modifier.imePadding()) {
        Divider(
            color = Slate_100
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
    onClick: () -> Unit,
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
private fun DiaryDetailScreenPreview() {
    DiaryDetailScreen(
        state = DiaryDetailScreenState(
            date = LocalDateTime.now()
        ),
        addVote = {},
        toggleDuplicateSelection = {},
        onBottomSheetContentChanged = {},
        onBottomSheetTitleChanged = {},
        openGallery = {},
        navigateToVote = {},
        onSelectedCategory = {},
        addOption = {},
        popToBackStack = {}
    )
}