package com.app.pmc.core.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun sendCode(email: String): Flow<String>
}