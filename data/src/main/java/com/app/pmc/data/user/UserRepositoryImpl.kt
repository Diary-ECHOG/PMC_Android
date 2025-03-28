package com.app.pmc.data.user

import com.app.pmc.core.repository.UserRepository
import com.app.pmc.data.model.VerifyRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override fun sendCode(email: String): Flow<String> = flow {
        userService.requestVerify(email = email).message?.let { emit(it) }
    }

    override fun verify(email: String, token: String): Flow<String> = flow {
        userService.verifyUser(
            VerifyRequest(
                email = email,
                token = token
            )
        ).message?.let { emit(it) }
    }
}