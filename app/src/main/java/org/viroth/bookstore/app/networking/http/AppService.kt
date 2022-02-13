package org.viroth.bookstore.app.networking.http


import org.viroth.bookstore.app.model.Book
import org.viroth.bookstore.app.model.BookInformation
import org.viroth.bookstore.app.model.TopBook
import org.viroth.bookstore.app.model.TopBookHydraMember
import retrofit2.Response
import retrofit2.http.*

interface AppService {
    @GET("/books")
    suspend fun book(
        @Query("title") title: String,
        @Query("page") page: Int = 1,
        @Query("author") author: String
    ): Response<Book>

    @GET("/books/{id}")
    suspend fun bookInformation(
        @Path("id") bookId: String
    ): Response<BookInformation>

    @GET("/top_books")
    suspend fun topBook(
        @Query("page") page: Int = 1,
    ): Response<TopBook>

    @GET("/top_books/{id}")
    suspend fun topBookInformation(
        @Path("id") bookId: Int
    ): Response<TopBookHydraMember>

}
