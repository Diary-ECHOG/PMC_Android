package com.app.pmc.data.core

import com.app.pmc.core.model.ErrorType

sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>

    sealed interface Failure : ApiResult<Nothing> {
        data class HttpError(
            val code: Int? = -1,
            val message: String? = "unknown",
            val body: String? = "unknown"
        ) : Failure {
            fun getError(): ErrorType {
                return when(this.code) {
                    401 -> ErrorType.UNAUTHORIZED
                    403 -> ErrorType.FORBIDDEN
                    else -> ErrorType.UNKNOWN
                }
            }
        }

        data class NetworkError(val throwable: Throwable) : Failure
        data class UnknownApiError(val throwable: Throwable) : Failure

        fun toThrowable(): Throwable = when (this) {
            is HttpError -> IllegalStateException("$code: $message\n$body")
            is NetworkError -> throwable
            is UnknownApiError -> throwable
        }
    }

    fun isSuccess(): Boolean = this is Success
    fun isFailure(): Boolean = this is Failure

    fun getOrThrow(): T {
        if (this is Failure) throw toThrowable()
        return (this as? Success<T>)?.data
            ?: throw IllegalStateException("No success result found.")
    }

    fun getOrNull(): T? = (this as? Success<T>)?.data

    fun failureOrNull(): Failure? = this as? Failure

    fun exceptionOrNull(): Throwable? = (this as? Failure)?.toThrowable()

    companion object {
        fun <T> successOf(data: T): ApiResult<T> = Success(data)
    }
}

