package com.app.pmc.data.core

import com.app.pmc.data.model.BaseResponse
import com.app.pmc.data.model.LoginRequest
import com.app.pmc.data.model.LoginResponse
import com.app.pmc.data.model.ReportRequest
import com.app.pmc.data.model.SignUpRequest
import com.app.pmc.data.model.SignUpResponse
import com.app.pmc.data.model.VerifyRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface OAuthService {
    @POST("api/users/login")
    suspend fun login(
        @Body request: LoginRequest
    ): BaseResponse<LoginResponse>
}
