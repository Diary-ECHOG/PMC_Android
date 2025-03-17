package com.app.pmc.core.usecase

import javax.inject.Inject

class SendCodeUseCase @Inject constructor(
   private val repository: UserRepository
) {
    operator fun invoke() = repository.sendCode()
}