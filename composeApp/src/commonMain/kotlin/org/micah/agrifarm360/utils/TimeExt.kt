package org.micah.agrifarm360.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

/**
 * Timestamp -> Date (e.g. "7 October 2021")
 */
fun Long.timestampToDate(): String {
    return try {
        val instant = Instant.fromEpochMilliseconds(this)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        "${dateTime.dayOfMonth} ${monthName(dateTime.month)} ${dateTime.year}"
    } catch (e: Exception) {
        toString()
    }
}

/**
 * Timestamp -> Date & Time (e.g. "Oct 7, 2021 9:00 AM")
 */
fun Long.timestampToDateTime(): String {
    return try {
        val instant = Instant.fromEpochMilliseconds(this)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val hour12 = when {
            dateTime.hour == 0 -> 12
            dateTime.hour > 12 -> dateTime.hour - 12
            else -> dateTime.hour
        }

        val amPm = if (dateTime.hour < 12) "AM" else "PM"
        val minute = dateTime.minute.toString().padStart(2, '0')

        "${monthShort(dateTime.month)} ${dateTime.dayOfMonth}, ${dateTime.year} $hour12:$minute $amPm"
    } catch (e: Exception) {
        toString()
    }
}

private fun monthName(month: Month): String = when (month) {
    Month.JANUARY -> "January"
    Month.FEBRUARY -> "February"
    Month.MARCH -> "March"
    Month.APRIL -> "April"
    Month.MAY -> "May"
    Month.JUNE -> "June"
    Month.JULY -> "July"
    Month.AUGUST -> "August"
    Month.SEPTEMBER -> "September"
    Month.OCTOBER -> "October"
    Month.NOVEMBER -> "November"
    Month.DECEMBER -> "December"
}

private fun monthShort(month: Month): String = when (month) {
    Month.JANUARY -> "Jan"
    Month.FEBRUARY -> "Feb"
    Month.MARCH -> "Mar"
    Month.APRIL -> "Apr"
    Month.MAY -> "May"
    Month.JUNE -> "Jun"
    Month.JULY -> "Jul"
    Month.AUGUST -> "Aug"
    Month.SEPTEMBER -> "Sep"
    Month.OCTOBER -> "Oct"
    Month.NOVEMBER -> "Nov"
    Month.DECEMBER -> "Dec"
}

/**
 * Gets the current system time in milliseconds.
 */
fun getCurrentTimestamp(): Long = Clock.System.now().toEpochMilliseconds()
