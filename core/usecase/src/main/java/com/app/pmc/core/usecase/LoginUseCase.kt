package com.app.pmc.core.usecase

import com.app.pmc.core.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(email: String, password: String) = repository.login(email = email, password = password)
}