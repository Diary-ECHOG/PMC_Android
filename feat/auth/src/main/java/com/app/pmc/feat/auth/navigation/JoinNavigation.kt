package com.app.pmc.feat.auth.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.pmc.feat.auth.UserInfoScreen


const val ROUTE_JOIN = "join"
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.addJoinGraph(navController: NavController) {
    composable(ROUTE_JOIN) {
        UserInfoScreen()
    }
}