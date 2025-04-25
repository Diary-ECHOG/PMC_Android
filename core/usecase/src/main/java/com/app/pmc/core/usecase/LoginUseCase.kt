package com.app.pmc.core.usecase

import com.app.pmc.core.model.EchogResult
import com.app.pmc.core.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(email: String = "", password: String = ""): Flow<EchogResult> {
        return if (email.isEmpty() && password.isEmpty()) repository.autoLogin()
        else repository.login(email, password)
    }
}