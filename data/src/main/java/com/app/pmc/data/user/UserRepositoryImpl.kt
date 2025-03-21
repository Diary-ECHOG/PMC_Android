package com.app.pmc.data.user

import com.app.pmc.core.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
): UserRepository {
    override fun sendCode() {
        // send code
    }
}