package com.monogatari.app.core.utils.extensions

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("NewApi")
fun String.toLocalDate(): LocalDate? {
    return try {
        // First, try the ISO format with timezone
        if (this.contains("T") && (this.contains("Z") || this.contains("["))) {
            val zonedDateTime = ZonedDateTime.parse(this)
            return zonedDateTime.toLocalDate()
        }

        // If it's not ISO format, we try with "d MMMM yyyy"
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)
        LocalDate.parse(this, formatter)
    } catch (e: Exception) {
        null
    }
}

@SuppressLint("NewApi")
fun String.toFormattedDate(outputPattern: String = "dd MMMM yyyy", locale: Locale = Locale("es")): String {
    val localDate = this.toLocalDate() ?: return this
    val formatter = DateTimeFormatter.ofPattern(outputPattern, locale)
    return localDate.format(formatter)
}