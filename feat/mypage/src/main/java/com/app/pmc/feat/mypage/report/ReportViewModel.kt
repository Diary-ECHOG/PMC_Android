package com.app.pmc.feat.mypage.report

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor() : ViewModel(), ContainerHost<ReportState, Unit> {
    override val container: Container<ReportState, Unit> = container(ReportState())

}

data class ReportState(
    val reportList: List<Report> = emptyList()
)

data class Report(
    val id: String = "",
    val state: String = "",
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val isVoted: Boolean = false,
)