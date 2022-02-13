package org.viroth.bookstore.app.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtil {
    @SuppressLint("SimpleDateFormat")
    fun formatTimeToStandard(source: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd")
            source.format(formatter)
        } else {
            val formatter = SimpleDateFormat("yyyy-MMM-dd")
            formatter.format(source)
        }
    }
}