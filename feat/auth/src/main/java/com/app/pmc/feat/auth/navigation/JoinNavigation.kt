package com.app.pmc.feat.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.pmc.feat.auth.join.UserInfoScreen
import com.app.pmc.feat.auth.login.FindPasswordScreen
import com.app.pmc.feat.auth.login.LoginScreen


const val ROUTE_JOIN = "join"
const val ROUTE_LOGIN = "login"
const val ROUTE_FIND_PASSWORD = "find_password"

fun NavController.navigateToJoin() {
    navigate(ROUTE_JOIN)
}

fun NavController.navigateToLogin() {
    navigate(ROUTE_LOGIN) {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
    }
}

fun NavGraphBuilder.addJoinGraph(
    navigateToMain: () -> Unit,
    navigateToGuestMain: () -> Unit,
    showSnackBar: (String) -> Unit,
    navController: NavController
) {
    composable(ROUTE_JOIN) {
        UserInfoScreen(
            showSnackBar = showSnackBar
        )
    }
    composable(ROUTE_LOGIN) {
        LoginScreen(
            navigateToMain = navigateToMain,
            navigateToFindPassword = { navController.navigate(ROUTE_FIND_PASSWORD) },
            onBrowseAsGuestButtonClicked = {
                navigateToGuestMain()
            },
            showSnackBar = showSnackBar
        )
    }
    composable(ROUTE_FIND_PASSWORD) {
        FindPasswordScreen(
            showSnackBar = showSnackBar
        )
    }
}