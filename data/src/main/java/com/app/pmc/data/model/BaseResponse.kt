package com.app.pmc.data.model

import com.app.pmc.data.core.ApiResult
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val statusCode: Int,
    val message: String?,
    val data: T?
) {
    fun requireSuccess(): T {
        if (!isSuccessful) throw IllegalStateException(message ?: "Unknown error")
        return data!!
    }

    private val isSuccessful: Boolean
        get() = statusCode == 200 && data != null
}

fun <T> BaseResponse<T>.getOrThrow(): T {
    if (!isSuccessful) throw IllegalStateException(message ?: "Unknown error")
    return data!!
}

fun <T> BaseResponse<T>.toResult(): ApiResult<T> {
    return if (isSuccessful) {
        ApiResult.Success(data!!)
    } else {
        ApiResult.Failure.HttpError(code = statusCode, message = message)
    }
}

private val <T> BaseResponse<T>.isSuccessful: Boolean
    get() = statusCode == 200 && data != null