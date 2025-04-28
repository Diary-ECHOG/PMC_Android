package com.app.pmc.core.model

data class SuccessResult<T>(val data: T? = null) : EchogResult<T>()
data class ErrorResult(val message: String? = null) : EchogResult<Nothing>()
sealed class EchogResult<out T>()
