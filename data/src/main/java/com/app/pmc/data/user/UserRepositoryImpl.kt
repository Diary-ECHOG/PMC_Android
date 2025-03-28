package com.app.pmc.data.user

import com.app.pmc.core.repository.UserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override fun sendCode(email: String) = flow {
        emit(userService.requestVerify(email = email))
    }
}