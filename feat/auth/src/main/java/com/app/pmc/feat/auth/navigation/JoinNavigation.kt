package com.app.pmc.feat.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.pmc.feat.auth.UserInfoScreen


const val ROUTE_JOIN = "join"

fun NavController.navigateToJoin() {
    navigate(ROUTE_JOIN)
}

fun NavGraphBuilder.addJoinGraph(navController: NavController) {
    composable(ROUTE_JOIN) {
        UserInfoScreen()
    }
}