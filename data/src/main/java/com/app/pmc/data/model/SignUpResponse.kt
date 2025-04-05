package com.app.pmc.data.model

data class SignUpResponse(
    val id: String,
    val nickname: String,
    val email: String,
    val enabled: Boolean,
)
