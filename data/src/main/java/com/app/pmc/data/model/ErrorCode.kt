package com.app.pmc.data.model

enum class ErrorCode(val code: Int) {
    AUTHENTICATION_EXPIRED(401),
    FORBIDDEN(403);
}