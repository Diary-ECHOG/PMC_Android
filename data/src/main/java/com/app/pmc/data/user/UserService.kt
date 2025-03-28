package com.app.pmc.data.user

import com.app.pmc.data.model.BaseResponse
import com.app.pmc.data.model.ReportRequest
import com.app.pmc.data.model.VerifyRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("api/users/verify")
    suspend fun verifyUser(
        @Body request: VerifyRequest,
    ): BaseResponse<String>

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
