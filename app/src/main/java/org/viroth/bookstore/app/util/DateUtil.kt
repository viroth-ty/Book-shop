package org.viroth.bookstore.app.util

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtil {
    @SuppressLint("SimpleDateFormat")
    fun formatTimeToStandard(source: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dateTime: ZonedDateTime = ZonedDateTime.parse(source)
            dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        } else {
            val date: org.joda.time.LocalDate? = org.joda.time.LocalDate.parse(source)
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            formatter.format(date)
        }
    }
}