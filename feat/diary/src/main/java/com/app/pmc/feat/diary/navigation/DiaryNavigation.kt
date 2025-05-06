package com.app.pmc.feat.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.pmc.feat.diary.AddDiaryScreen
import com.app.pmc.feat.diary.diarydetail.DiaryDetailScreen

const val ROUTE_DIARY = "diary"
const val ROUTE_DIARY_DETAIL = "diary_detail"

fun NavController.navigateToAddDiary() {
    this.navigate(ROUTE_DIARY)
}

fun NavController.navigateToDiary() {
    this.navigate(ROUTE_DIARY_DETAIL)
}

fun NavGraphBuilder.addDiaryGraph(
    navController: NavController,
    navigateToVote: () -> Unit,
    navigateToHome: () -> Unit
) {
    composable(ROUTE_DIARY) {
        AddDiaryScreen(
            popToBackStack = navigateToHome,
            navigateToVote = navigateToVote,
        )
    }
    composable(ROUTE_DIARY_DETAIL) {
        DiaryDetailScreen(
            popToBackStack = navigateToHome,
            navigateToVote = navigateToVote
        )
    }
}