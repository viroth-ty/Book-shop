package org.viroth.bookstore.app.networking.repository

import org.viroth.bookstore.app.model.Book
import org.viroth.bookstore.app.model.BookInformation
import org.viroth.bookstore.app.model.Query
import org.viroth.bookstore.app.networking.http.AppService
import org.viroth.bookstore.app.networking.http.ResultOf
import org.viroth.bookstore.app.networking.http.safeApiCall

class AppRepository(
    private val appService: AppService
) {
    suspend fun book(query: Query): ResultOf<Book> {
        return safeApiCall {
            appService.book(
                page = query.page,
                title = query.title,
                author = query.author
            )
        }
    }

    suspend fun bookInformation(bookId: String): ResultOf<BookInformation> {
        return safeApiCall {
            appService.bookInformation(bookId = bookId)
        }
    }

    suspend fun topBook(query: Query): ResultOf<Book>  {
        return safeApiCall {
            appService.topBook(page = query.page)
        }
    }
}