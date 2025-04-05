package com.app.pmc.core.repository

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
}