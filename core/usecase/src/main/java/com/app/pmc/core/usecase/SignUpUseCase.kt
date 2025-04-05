package com.app.pmc.core.usecase

import com.app.pmc.core.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(email: String, password: String, agreement: Boolean) =
        repository.signUp(email = email, password = password, agreement = agreement)
}