package com.app.pmc.data.user

import com.app.pmc.data.model.BaseResponse
import com.app.pmc.data.model.ReportRequest
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("api/users/verify")
    suspend fun verifyUser(report: ReportRequest): ReportRequest

    @POST("api/users/request-verification")
    suspend fun requestVerify(
        @Query("email") email: String,
    ): BaseResponse<String>

    @POST("api/users/register")
    suspend fun registerUser(report: ReportRequest): ReportRequest

    @POST("api/users/login")
    suspend fun login(report: ReportRequest): ReportRequest

    @POST("api/users/delete")
    suspend fun withdraw(report: ReportRequest): ReportRequest
}
