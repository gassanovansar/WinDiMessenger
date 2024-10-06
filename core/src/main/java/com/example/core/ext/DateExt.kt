package com.example.core.ext

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Long.toDateIntUI(): String {
    val date = Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.UTC)
    return "${date.year}-${date.monthNumber.to()}-${date.dayOfMonth.to()}"
}

fun Int.to(): String {
    return if (this > 9) {
        this.toString()
    } else {
        "0$this"
    }
}

fun String.toLong(): Long? {
    return try {
        Instant.parse("${this}T00:00:00Z")
            .toEpochMilliseconds()
    } catch (e: Exception) {
        null
    }
}
