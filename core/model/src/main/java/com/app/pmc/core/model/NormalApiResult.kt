package com.app.pmc.core.model

sealed class EchogResult {
    data object Success : EchogResult()
    data class Error(val message: String? = null) : EchogResult()
}
