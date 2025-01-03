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
import com.app.pmc.feat.mypage.ROUTE_MY_PAGE
import com.app.pmc.feat.mypage.addMyPageGraph
import com.app.pmc.feat.vote.ROUTE_VOTE
import com.app.pmc.feat.vote.addVoteGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PMCNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = ROUTE_MY_PAGE) {
        addCalendarGraph(navController = navController)
        addJoinGraph(navController = navController)
        addHomeGraph(navController = navController)
        addVoteGraph(navController = navController)
        addMyPageGraph(navController = navController)
    }
}