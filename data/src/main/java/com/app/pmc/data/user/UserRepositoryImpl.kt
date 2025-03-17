package com.app.pmc.data.user

import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
): UserRepository {
    override fun sendCode() {
        // send code
    }
}