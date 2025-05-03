package com.app.pmc.data.local

interface UserLocalDataSource {
    suspend fun saveUserId(userId: String)
    suspend fun getUserId(): String?
    suspend fun deleteUserId()
    suspend fun saveToken(token: String)
    fun getToken(): String?
    suspend fun deleteToken()
    suspend fun saveRefreshToken(refreshToken: String)
    suspend fun getRefreshToken(): String?
    suspend fun deleteRefreshToken()
    suspend fun getPassword(): String?
    suspend fun setPassword(password: String)
}