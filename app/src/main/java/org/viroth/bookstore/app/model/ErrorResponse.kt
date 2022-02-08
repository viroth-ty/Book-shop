package org.viroth.bookstore.app.model


data class ErrorResponse(
    val success: Boolean? = null,
    val message: String? = null,
)