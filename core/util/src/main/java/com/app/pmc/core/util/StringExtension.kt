package com.app.pmc.core.util

import com.app.pmc.core.util.StringExtension.toDiaryDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object StringExtension {
    fun String.toDiaryDate(): String {
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 dd일")
        return getCorrectedDate().format(outputFormatter)
    }

    private fun String.getCorrectedDate(): LocalDateTime {
        val corrected = this.replaceFirst(
            Regex("(\\d{2}):(\\d{2}):(\\d{2}):(\\d{1,6})"),
            "$1:$2:$3.$4"
        )

        val padded = corrected.replace(
            Regex("\\.(\\d{1,5})$")
        ) {
            ".${it.groupValues[1].padEnd(6, '0')}"
        }

        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        val dateTime = LocalDateTime.parse(padded, inputFormatter)

        return dateTime
    }

    fun String.getMonth(): Int = this.getCorrectedDate().monthValue
    fun String.isToday(): Boolean = this.getCorrectedDate().toLocalDate() == LocalDate.now()
    fun String.isYesterday(): Boolean = this.getCorrectedDate().toLocalDate().plusDays(1L) == LocalDate.now()
}