package com.app.pmc.data.core

import com.app.pmc.data.local.UserLocalDataSource
import com.app.pmc.data.model.ErrorCode
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class OAuthHeaderInterceptor @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val dataSource: UserLocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = tokenProvider.getAccessToken()
        if (token.isNullOrEmpty()) {
            return chain.proceed(originalRequest)
        }

        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        val response = chain.proceed(newRequest)

        when (response.code) {
            ErrorCode.AUTHENTICATION_EXPIRED.code, ErrorCode.FORBIDDEN.code -> {
                if (token == tokenProvider.getAccessToken())
                    runBlocking {
                        dataSource.deleteToken()
                        dataSource.deleteRefreshToken()
                        dataSource.deleteUserId()
                    }
            }
        }
        return response
    }
}