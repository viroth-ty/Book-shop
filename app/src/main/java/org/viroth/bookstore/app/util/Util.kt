package org.viroth.bookstore.app.util

import org.viroth.bookstore.app.R
import java.util.*


class Util {
    companion object {
        fun findBookId(resource: String): String = resource.substring(startIndex = 7)
    }
}