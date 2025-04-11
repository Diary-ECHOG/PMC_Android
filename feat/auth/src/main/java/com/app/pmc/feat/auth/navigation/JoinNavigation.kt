package com.app.pmc.feat.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.pmc.feat.auth.join.UserInfoScreen
import com.app.pmc.feat.auth.login.LoginScreen


const val ROUTE_JOIN = "join"
const val ROUTE_LOGIN = "login"

fun NavController.navigateToJoin() {
    navigate(ROUTE_JOIN)
}

fun NavController.navigateToLogin() {
    navigate(ROUTE_LOGIN)
}

fun NavGraphBuilder.addJoinGraph(navigateToMain: () -> Unit, showSnackBar: (String) -> Unit) {
    composable(ROUTE_JOIN) {
        UserInfoScreen(
            showSnackBar = showSnackBar
        )
    }
    composable(ROUTE_LOGIN) {
        LoginScreen(
            navigateToMain = navigateToMain,
            showSnackBar = showSnackBar
        )
    }
}