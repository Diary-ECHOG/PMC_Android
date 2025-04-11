package com.app.pmc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.pmc.feat.auth.navigation.ROUTE_LOGIN
import com.app.pmc.feat.auth.navigation.addJoinGraph
import com.app.pmc.feat.auth.navigation.navigateToJoin
import com.app.pmc.feat.calendar.navigation.addCalendarGraph
import com.app.pmc.feat.diary.navigation.addDiaryGraph
import com.app.pmc.feat.diary.navigation.navigateToAddDiary
import com.app.pmc.feat.home.ROUTE_HOME
import com.app.pmc.feat.home.addHomeGraph
import com.app.pmc.feat.home.navigateToHome
import com.app.pmc.feat.mypage.addMyPageGraph
import com.app.pmc.feat.splash.ROUTE_SPLASH
import com.app.pmc.feat.splash.addSplashGraph
import com.app.pmc.feat.vote.addVoteGraph
import com.app.pmc.feat.vote.navigateVote

@Composable
fun PMCNavHost(
    navController: NavHostController,
    showSnackBar: (String) -> Unit
) {
    NavHost(navController = navController, startDestination = ROUTE_LOGIN) {
        addCalendarGraph(navController = navController)
        addJoinGraph(navigateToMain = {
            navController.navigateToHome()
        }, showSnackBar = showSnackBar)
        addSplashGraph(
            navController = navController,
            onNavigateToJoinScreen = {
                navController.navigateToJoin()
            },
            onNavigateToLoginScreen = {
                navController.navigateToJoin()
            }
        )
        addHomeGraph(
            navController = navController, navigateToVoteList = {
                navController.navigateVote()
            },
            navigateToAddDiary = {
                navController.navigateToAddDiary()
            }
        )
        addVoteGraph(navController = navController)
        addMyPageGraph(navController = navController)
        addDiaryGraph(navController = navController, navigateToVote = {
            navController.navigateVote()
        })
    }
}