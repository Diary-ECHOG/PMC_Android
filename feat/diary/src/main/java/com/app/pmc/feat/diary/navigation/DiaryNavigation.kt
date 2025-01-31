package com.app.pmc.feat.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.pmc.feat.diary.AddDiaryScreen

const val ROUTE_DIARY = "diary"

fun NavController.navigateToAddDiary() {
    this.navigate(ROUTE_DIARY)
}

fun NavGraphBuilder.addDiaryGraph(
    navController: NavController,
    navigateToVote: () -> Unit
) {
    composable(ROUTE_DIARY) {
        AddDiaryScreen(
            popToBackStack = {
                navController.popBackStack()
            },
            navigateToVote = navigateToVote
        )
    }
}