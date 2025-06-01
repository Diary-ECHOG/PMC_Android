package com.app.pmc.data.core

import com.app.pmc.data.local.UserLocalDataSource
import com.app.pmc.data.model.BaseResponse
import com.app.pmc.data.model.LoginRequest
import com.app.pmc.data.model.LoginResponse
import com.app.pmc.data.user.UserService
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class OAuthenticator @Inject constructor(
    private val tokenProvider: TokenProvider,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= 2) return null
        if (response.request.header("Authorization") == "Bearer ${tokenProvider.getAccessToken()}") {
            val newToken = tokenProvider.refreshToken() ?: return null
            return response.request.newBuilder()
                .header("Authorization", "Bearer $newToken")
                .build()
        }
        return null
    }

    private fun responseCount(response: Response): Int {
        var count = 1
        var prior = response.priorResponse
        while (prior != null) {
            count++
            prior = prior.priorResponse
        }
        return count
    }
}

class TokenProvider @Inject constructor(
    private val dataSource: UserLocalDataSource,
    private val authService: OAuthService
) {
    fun getAccessToken(): String? = dataSource.getToken().takeIf { it?.isNotBlank() == true }
    fun refreshToken(): String? = runBlocking {
        val userId = dataSource.getUserId()
        val password = dataSource.getPassword()

        if (userId.isNullOrBlank() || password.isNullOrBlank()) {
            return@runBlocking null
        }

        val response = authService.login(
            LoginRequest(
                loginId = userId,
                password = password
            )
        )
        if (response.statusCode == 200) {
            val newToken = response.data?.token
            newToken?.let {
                dataSource.deleteToken()
                dataSource.saveToken(it)
            }
            return@runBlocking newToken
        } else {
            return@runBlocking null
        }
    }
}