package com.app.pmc.data.model

data class ReportRequest(
    val reportType: String,
    val reportTarget: ReportTarget,
    val reportContent: String,
    val targetUserId: String
)

data class ReportTarget(
    val targetType: String,
    val targetId: String
)