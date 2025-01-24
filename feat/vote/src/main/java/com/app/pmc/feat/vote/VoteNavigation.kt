package com.app.pmc.feat.vote

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_VOTE = "vote"

fun NavController.navigateVote() {
    this.navigate(ROUTE_VOTE)
}

fun NavGraphBuilder.addVoteGraph(navController: NavController) {
    composable(ROUTE_VOTE) {
        VoteListScreen()
    }
}