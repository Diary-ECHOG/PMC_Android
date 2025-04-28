package com.app.pmc.data.core

import com.app.pmc.data.local.UserLocalDataSource
import com.app.pmc.data.user.UserService
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class OAuthenticator @Inject constructor(
    private val tokenProvider: TokenProvider,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.request.header("Authorization") == "Bearer ${tokenProvider.getAccessToken()}") {
            // 새 액세스 토큰 요청
            val newToken = tokenProvider.refreshToken() ?: return null

            // 새로운 토큰으로 요청 재시도
            return response.request.newBuilder()
                .header("Authorization", "Bearer $newToken")
                .build()
        }
        return null
    }
}

class TokenProvider @Inject constructor(
    private val dataSource: UserLocalDataSource
) {
    private var accessToken: String? = null

    fun getAccessToken(): String? = dataSource.getToken()

    fun refreshToken(): String? {
        val newToken = requestNewAccessToken()
        accessToken = newToken
        return newToken
    }

    //todo : 토큰 갱신 로직 추가
    private fun requestNewAccessToken(): String {
        return "new_access_token"
    }
}