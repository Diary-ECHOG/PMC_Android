package com.app.pmc.feat.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_SPLASH = "splash"

fun NavGraphBuilder.addSplashGraph(
    navController: NavController,
    onNavigateToJoinScreen: () -> Unit
) {
    composable(ROUTE_SPLASH) {
        SplashScreen(
            onNavigateToJoinScreen = onNavigateToJoinScreen
        )
    }
}