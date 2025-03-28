package com.app.pmc.core.usecase

import com.app.pmc.core.repository.UserRepository
import javax.inject.Inject

class SendCodeUseCase @Inject constructor(
   private val repository: UserRepository
) {
    operator fun invoke(email: String) = repository.sendCode(email = email)
}