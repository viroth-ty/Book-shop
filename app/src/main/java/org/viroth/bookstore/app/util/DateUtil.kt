package org.viroth.bookstore.app.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateUtil {
    companion object {

        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.O)
        fun formatTimeToStandard(source: String): String {
            val sourceFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
            val date = LocalDateTime.parse(source, sourceFormatter)
            return timeFormatter.format(date)
        }

    }
}