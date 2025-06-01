package com.app.pmc.feat.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.pmc.feat.diary.AddDiaryScreen
import com.app.pmc.feat.diary.diarydetail.DiaryDetailScreen

const val ROUTE_DIARY = "diary"
const val ROUTE_DIARY_DETAIL = "diary_detail/{id}"
const val ROUTE_DIARY_DETAIL_ARGUMENT_ID = "id"

fun NavController.navigateToAddDiary() {
    this.navigate(ROUTE_DIARY)
}

fun NavController.navigateToDiary(id: String) {
    this.navigate(ROUTE_DIARY_DETAIL.replace(ROUTE_DIARY_DETAIL_ARGUMENT_ID, id))
}

fun NavGraphBuilder.addDiaryGraph(
    navController: NavController,
    navigateToVote: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit
) {
    composable(ROUTE_DIARY) {
        AddDiaryScreen(
            popToBackStack = navigateToHome,
            navigateToVote = navigateToVote,
            navigateToLogin = navigateToLogin,
        )
    }
    composable(
        route = ROUTE_DIARY_DETAIL,
        arguments = listOf(navArgument(ROUTE_DIARY_DETAIL_ARGUMENT_ID) {
            type = androidx.navigation.NavType.StringType
            defaultValue = ""
        })
    ) { backStackEntry ->
        val id =
            backStackEntry.arguments?.getString(ROUTE_DIARY_DETAIL_ARGUMENT_ID) ?: return@composable
        DiaryDetailScreen(
            popToBackStack = navigateToHome,
        )
    }
}