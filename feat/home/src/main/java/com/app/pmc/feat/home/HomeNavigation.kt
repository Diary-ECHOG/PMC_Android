package com.app.pmc.feat.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_HOME = "home"
const val ROUTE_HOME_GUEST = "home_guest"

fun NavController.navigateToHome() {
    navigate(ROUTE_HOME)
}

fun NavController.navigateToGuestMain() {
    navigate(ROUTE_HOME_GUEST)
}

fun NavController.navigateToHomeToTop() {
    navigate(ROUTE_HOME_GUEST) {
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
    composable(ROUTE_HOME_GUEST) {
        GuestHomeScreen(
            navigateToVoteList = navigateToVoteList,
            navigateToMyPage = {
                navController.navigate("mypage")
            },
            addDiary = navigateToAddDiary
        )
    }
}