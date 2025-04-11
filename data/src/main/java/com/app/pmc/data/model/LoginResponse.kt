package com.app.pmc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val nickname: String,
    val email: String,
    val token: String,
    val refreshToken: String,
)
