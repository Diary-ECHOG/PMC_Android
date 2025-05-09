package com.app.pmc.feat.mypage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.pmc.feat.mypage.report.ReportScreen
import com.app.pmc.feat.mypage.withdraw.WithdrawScreen

const val ROUTE_MY_PAGE = "mypage"
const val ROUTE_MY_VOTE = "myVote"
const val ROUTE_MY_REPORT = "myReport"
const val ROUTE_WITHDRAW = "withdraw"

    fun NavGraphBuilder.addMyPageGraph(navController: NavController, navigateToLogin: () -> Unit) {
        composable(ROUTE_MY_PAGE) {
            MyPageScreen(
                navigateToMyVoteList = {
                    navController.navigate(ROUTE_MY_VOTE)
                },
                navigateToMyReportList = {
                    navController.navigate(ROUTE_MY_REPORT)
                },
                navigateToPrivacyPolicy = {},
                navigateToVoteIParticipatedIn = {},
                navigateToLogin = navigateToLogin,
                navigateToWithdraw = {
                    navController.navigate(ROUTE_WITHDRAW)
                },
                popBackStack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = ROUTE_MY_VOTE
        ) {
            MyVoteListScreen(
                popToBackStack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = ROUTE_MY_REPORT
        ) {
            ReportScreen(
                popBackStack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = ROUTE_WITHDRAW
        ) {
            WithdrawScreen()
        }
    }