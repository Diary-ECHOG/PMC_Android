package com.app.pmc.data.core

internal enum class StatusCode(val code: String) {
    SUCCESS("200"),
    ERROR("500"),
    UNAUTHORIZED("401"),
}