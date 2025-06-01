package com.app.pmc.core.model

data class SuccessResult<T>(val data: T? = null) : EchogResult<T>()
data class ErrorResult(
    val errorType: ErrorType
) : EchogResult<Nothing>()

sealed class EchogResult<out T>()

enum class ErrorType(message: String) {
    UNAUTHORIZED("인증되지 않은 사용자입니다."),
    FORBIDDEN("접근이 금지된 사용자입니다."),
    NOT_FOUND("찾을 수 없는 사용자입니다."),
    BAD_REQUEST("잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("서버 내부 오류입니다."),
    UNKNOWN("알 수 없는 오류입니다."),
}
