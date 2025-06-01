package com.app.pmc.core.repository

import com.app.pmc.core.model.EchogResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun sendCode(email: String): Flow<String>
    fun verify(email: String, token: String): Flow<Boolean>
    fun signUp(
        email: String,
        nickname: String = "",
        password: String,
        agreement: Boolean,
        anonymous: Boolean = true
    ): Flow<String>
    fun login(email: String, password: String): Flow<EchogResult<String>>
    fun autoLogin(): Flow<EchogResult<String>>
    fun sendResetPasswordEmail(email: String): Flow<EchogResult<Boolean>>
    fun logout(): Flow<EchogResult<Boolean>>
}