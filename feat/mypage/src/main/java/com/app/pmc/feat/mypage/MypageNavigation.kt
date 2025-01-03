package com.app.pmc.feat.mypage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_MY_PAGE = "mypage"
    fun NavGraphBuilder.addMyPageGraph(navController: NavController) {
        composable(ROUTE_MY_PAGE) {
            MyPageScreen()
        }
    }