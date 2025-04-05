package com.app.pmc.data.model

import kotlinx.serialization.Serializable

//todo : 둘의 차이는?
@Serializable
data class SignUpRequest(
    val nickname: String = "",
    val email: String,
    val password: String,
    val agreement: Boolean,
    val anonymous: Boolean = true
)