package org.viroth.bookstore.app.networking.http


import org.viroth.bookstore.app.model.Book
import retrofit2.Response
import retrofit2.http.*

interface AppService {
    @GET("/books")
    suspend fun getBook(): Response<Book>
}