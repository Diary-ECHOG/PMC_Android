package com.app.pmc.data.user

import com.app.pmc.core.model.EchogResult
import com.app.pmc.core.repository.UserRepository
import com.app.pmc.data.core.ApiResult
import com.app.pmc.data.di.SafeApiCall
import com.app.pmc.data.local.UserLocalDataSource
import com.app.pmc.data.model.LoginRequest
import com.app.pmc.data.model.SignUpRequest
import com.app.pmc.data.model.VerifyRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val safeApiCall: SafeApiCall,
    private val userLocalDataSource: UserLocalDataSource,
) : UserRepository {
    override fun sendCode(email: String): Flow<String> = flow {
        when (val result = safeApiCall.execute { userService.requestVerify(email) }) {
            is ApiResult.Success -> emit(result.data.message.toString())
            is ApiResult.Failure -> result.toThrowable().localizedMessage?.let { emit(it) }
        }
    }

    override fun verify(email: String, token: String): Flow<Boolean> = flow {
        emit(safeApiCall.execute {
            userService.verifyUser(
                VerifyRequest(
                    email = email,
                    token = token
                )
            )
        }.isSuccess())
    }

    // todo : message 없을땐 어떻게..?
    override fun signUp(
        email: String,
        nickname: String,
        password: String,
        agreement: Boolean,
        anonymous: Boolean
    ): Flow<String> = flow {
        when (val result = safeApiCall.execute {
            userService.registerUser(
                SignUpRequest(
                    nickname = nickname.ifBlank { email },
                    email = email,
                    password = password,
                    agreement = agreement,
                    anonymous = anonymous,
                )
            )
        }) {
            is ApiResult.Success -> emit(result.data.message.toString())
            is ApiResult.Failure -> result.toThrowable().localizedMessage?.let { emit(it) }
        }
    }

    override fun login(email: String, password: String): Flow<EchogResult> {
        return flow {
            when (val result = safeApiCall.execute {
                userService.login(
                    LoginRequest(
                        loginId = email,
                        password = password
                    )
                )
            }) {
                //todo : 중복 확인
                is ApiResult.Success -> {
                    result.data.data?.let { loginResponse ->
                        userLocalDataSource.saveUserId(loginResponse.email)
                        userLocalDataSource.saveToken(loginResponse.token)
                        userLocalDataSource.saveRefreshToken(loginResponse.refreshToken)
                        emit(EchogResult.Success)
                    }
                }
                is ApiResult.Failure.HttpError -> {
                    emit(EchogResult.Error(result.getErrorMessage()))
                }
                else -> {
                    emit(EchogResult.Error(result.exceptionOrNull()?.localizedMessage.toString()))
                }
            }
        }
    }

    //todo : api 추가
    override fun sendResetPasswordEmail(email: String): Flow<EchogResult> = flow {}
}