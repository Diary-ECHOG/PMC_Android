package com.app.pmc.data.user

import com.app.pmc.core.repository.UserRepository
import com.app.pmc.data.di.SafeApiCall
import com.app.pmc.data.model.SignUpRequest
import com.app.pmc.data.model.VerifyRequest
import com.app.pmc.data.model.getOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val safeApiCall: SafeApiCall
) : UserRepository {
    override fun sendCode(email: String): Flow<String> = flow {
        safeApiCall.execute {
            userService.requestVerify(email = email)
        }.also {
            if(it.isSuccess()) it.getOrNull()?.let { emit(it.message.toString()) }
            if(it.isFailure()) it.failureOrNull()?.let { emit(it.toString()) }
        }
    }

    override fun verify(email: String, token: String): Flow<Boolean> = flow {
        safeApiCall.execute {
            userService.verifyUser(
                VerifyRequest(
                    email = email,
                    token = token
                )
                //todo ; 코드별로 구분 따로 달기
            ).message
        }
    }

    // todo : message 없을땐 어떻게..?
    override fun signUp(
        email: String,
        nickname: String,
        password: String,
        agreement: Boolean,
        anonymous: Boolean
    ): Flow<String> = flow {
        safeApiCall.execute {
            userService.registerUser(
                SignUpRequest(
                    nickname = nickname.ifBlank { email },
                    email = email,
                    password = password,
                    agreement = agreement,
                    anonymous = anonymous,
                )
            ).message
        }
    }
}