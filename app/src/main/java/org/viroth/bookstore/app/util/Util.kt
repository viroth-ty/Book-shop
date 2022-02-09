package org.viroth.bookstore.app.util

import org.viroth.bookstore.app.R
import java.util.*


class Util {
    companion object {
        fun findBookId(resource: String): String = resource.substring(startIndex = 7)
        fun randomImage(): Int {
            val images: ArrayList<Int> = arrayListOf(
                R.drawable.img_1,
                R.drawable.img_2,
                R.drawable.img_3,
                R.drawable.img_4,
                R.drawable.img_5,
                R.drawable.img_6,
                R.drawable.img_7,
                R.drawable.img_8,
                R.drawable.img_9,
                R.drawable.img_10,
                R.drawable.img_11,
                R.drawable.img_12,
                R.drawable.img_13,
                R.drawable.img_14,
                R.drawable.img_15,
                R.drawable.img_16
            )
            return images[Random().nextInt(images.size)]
        }
    }
}