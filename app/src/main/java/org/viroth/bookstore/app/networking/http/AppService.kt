package org.viroth.bookstore.app.networking.http


import org.viroth.bookstore.app.model.Book
import org.viroth.bookstore.app.model.BookInformation
import retrofit2.Response
import retrofit2.http.*

interface AppService {
    @GET("/books")
    suspend fun getBook(): Response<Book>

    @GET("/books/{id}")
    suspend fun getBookInformation(
        @Path("id") bookId: String
    ): Response<BookInformation>


}
