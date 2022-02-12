package org.viroth.bookstore.app.networking.repository

import android.content.Context
import org.viroth.bookstore.app.model.Book
import org.viroth.bookstore.app.model.BookInformation
import org.viroth.bookstore.app.model.Query
import org.viroth.bookstore.app.networking.http.AppService
import org.viroth.bookstore.app.networking.http.ResultOf
import org.viroth.bookstore.app.networking.http.safeApiCall

class AppRepository(
    private val appService: AppService
) {
    suspend fun getBook(query: Query): ResultOf<Book> {
        return safeApiCall {
            appService.getBook(
                page = query.page,
                title = query.title,
                author = query.author
            )
        }
    }

    suspend fun getBookInformation(bookId: String): ResultOf<BookInformation> {
        return safeApiCall {
            appService.getBookInformation(bookId = bookId)
        }
    }
}