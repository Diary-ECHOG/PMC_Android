package com.app.pmc.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.pmc.feat.calendar.navigation.addCalendarGraph
import com.app.pmc.feat.home.ROUTE_HOME
import com.app.pmc.feat.home.addHomeGraph
import com.app.pmc.feat.auth.navigation.addJoinGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PMCNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = ROUTE_HOME) {
        addCalendarGraph(navController = navController)
        addJoinGraph(navController = navController)
        addHomeGraph(navController = navController)
    }
}