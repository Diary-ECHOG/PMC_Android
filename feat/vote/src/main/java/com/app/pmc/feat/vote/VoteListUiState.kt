package com.app.pmc.feat.vote

data class VoteListUiState(
    val voteList: List<Vote> = emptyList(),
    val categoryList: List<String> = emptyList(),
    val selectedCategory: String = "",
)

data class Vote(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val voteCount: Int = 0,
    val nickname: String = "",
    val date: String = "",
    val isVoted: Boolean = false,
)