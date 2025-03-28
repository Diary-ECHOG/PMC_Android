package com.app.pmc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VerifyRequest (
    val email: String,
    val token: String,
)