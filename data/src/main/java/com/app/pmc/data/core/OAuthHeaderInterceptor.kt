package com.app.pmc.data.core

import okhttp3.Interceptor
import okhttp3.Response

class OAuthHeaderInterceptor(
    private val tokenProvider: TokenProvider
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // 현재 저장된 액세스 토큰 가져오기
        val token = tokenProvider.getAccessToken()

        // 토큰이 없으면 그냥 기존 요청 반환
        if (token.isNullOrEmpty()) {
            return chain.proceed(originalRequest)
        }

        // Authorization 헤더 추가한 새 요청 생성
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}