package com.app.pmc.feat.mypage.report

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class ReportViewModel: ViewModel(), ContainerHost<ReportState, Unit> {
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