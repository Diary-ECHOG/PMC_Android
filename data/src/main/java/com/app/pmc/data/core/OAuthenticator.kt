package com.app.pmc.data.core

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class OAuthenticator @Inject constructor(
    private val tokenProvider: TokenProvider
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // 토큰이 만료되었는지 확인
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

class TokenProvider @Inject constructor() {
    private var accessToken: String? = null

    fun getAccessToken(): String? = accessToken

    fun refreshToken(): String? {
        // 네트워크 요청을 통해 새 토큰 받아오기
        val newToken = requestNewAccessToken()
        accessToken = newToken
        return newToken
    }

    private fun requestNewAccessToken(): String? {
        // 실제 네트워크 요청 코드 추가
        return "new_access_token"
    }
}