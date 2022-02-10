package org.viroth.bookstore.app.model

data class Query(
    var page: Int = 1,
    var title: String,
    var author: String,
)