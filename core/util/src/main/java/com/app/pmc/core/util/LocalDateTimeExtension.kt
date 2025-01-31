package com.app.pmc.core.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LocalDateTimeExtension {
    fun LocalDateTime?.toDiaryDate(): String {
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE")
        return this?.format(formatter) ?: ""
    }
}