package com.app.pmc.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.pmc.feat.auth.navigation.addJoinGraph
import com.app.pmc.feat.calendar.navigation.addCalendarGraph
import com.app.pmc.feat.home.ROUTE_HOME
import com.app.pmc.feat.home.addHomeGraph
import com.app.pmc.feat.mypage.addMyPageGraph
import com.app.pmc.feat.vote.addVoteGraph
import com.app.pmc.feat.vote.navigateVote

@Composable
fun PMCNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = ROUTE_HOME) {
        addCalendarGraph(navController = navController)
        addJoinGraph(navController = navController)
        addHomeGraph(navController = navController, navigateToVoteList = {
            navController.navigateVote()
        })
        addVoteGraph(navController = navController)
        addMyPageGraph(navController = navController)
    }
}