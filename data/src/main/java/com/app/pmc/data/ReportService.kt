package com.app.pmc.data

import com.app.pmc.data.model.ReportRequest
import retrofit2.http.POST

interface ReportService {
    @POST("api/report")
    suspend fun report(report: ReportRequest): ReportRequest
}
