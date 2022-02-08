package org.viroth.bookstore.app.networking.http

import org.viroth.bookstore.app.model.ErrorResponse

sealed class ResultOf<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultOf<T>()
    data class Error(val code: Int? = null, val error: ErrorResponse? = null) : ResultOf<Nothing>()
    object NetworkError : ResultOf<Nothing>()
}