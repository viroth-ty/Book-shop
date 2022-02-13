package org.viroth.bookstore.app.util

import android.graphics.Color
import java.util.*


object Util {
    fun findBookId(resource: String): String = resource.substring(startIndex = 7)
    fun splitTheWord(resource: String): String = resource.split("\\s+".toRegex())[0]
    fun generateColorFromString(seed: String): Int = Color.parseColor("#FF%06X".format(0xFFFFFF and seed.hashCode()))

}