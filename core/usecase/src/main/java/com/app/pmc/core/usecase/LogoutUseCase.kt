package com.app.pmc.core.usecase

import com.app.pmc.core.model.EchogResult
import com.app.pmc.core.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<EchogResult<Boolean>> = repository.logout()
}