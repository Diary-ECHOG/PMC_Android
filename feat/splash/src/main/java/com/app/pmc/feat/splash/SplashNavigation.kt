package com.app.pmc.feat.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_SPLASH = "splash"

fun NavGraphBuilder.addSplashGraph(
    onNavigateToLoginScreen: () -> Unit,
    onNavigateToJoinScreen: () -> Unit = {},
    onNavigateToMainScreen: () -> Unit = {},
) {
    composable(ROUTE_SPLASH) {
        SplashScreen(
            onNavigateToLoginScreen = onNavigateToLoginScreen,
            onNavigateToJoinScreen = onNavigateToJoinScreen,
            onNavigateToMainScreen = onNavigateToMainScreen
        )
    }
}