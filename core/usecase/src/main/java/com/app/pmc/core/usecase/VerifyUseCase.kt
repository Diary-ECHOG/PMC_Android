package com.app.pmc.core.usecase

import com.app.pmc.core.repository.UserRepository
import javax.inject.Inject

class VerifyUseCase @Inject constructor(
   private val repository: UserRepository
) {
    operator fun invoke(email: String, token: String) = repository.verify(email = email, token = token)
}