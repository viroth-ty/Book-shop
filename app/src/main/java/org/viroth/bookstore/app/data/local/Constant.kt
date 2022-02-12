package org.viroth.bookstore.app.data.local
class Constant {
    object Seat {
        const val AVAILABLE = "available"
        const val UNAVAILABLE = "unavailable"
    }

    object ThemeDelegate {
        const val KEY = "theme"
        const val DARK = 1
        const val LIGHT = 2
        const val YELLOW = 2
    }

    object Book {
        const val BOOKING_ID = "book_id"
        const val BOOKING_ISBN = "book_isbn"
    }

    object SearchBy {
        const val AUTHOR = "author"
        const val TITLE = "title"
    }
}