package com.app.pmc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T> (
    val statusCode: String,
    val message: String? = null,
    val data: T? = null
)