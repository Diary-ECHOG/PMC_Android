package com.app.pmc.feat.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_HOME = "home"

fun NavController.navigateToHome() {
    navigate(ROUTE_HOME)
}

fun NavController.navigateToHomeToTop() {
    navigate(ROUTE_HOME) {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
    }
}

fun NavGraphBuilder.addHomeGraph(
    navController: NavController,
    navigateToVoteList: () -> Unit,
    navigateToAddDiary: () -> Unit,
    navigateToDiary: (String) -> Unit
) {
    composable(ROUTE_HOME) {
        HomeScreen(
            navigateToVoteList = navigateToVoteList,
            navigateToMyPage = {
                navController.navigate("mypage")
            },
            navigateToDiary = { id ->
                navigateToDiary(id)
            },
            addDiary = navigateToAddDiary
        )
    }
}