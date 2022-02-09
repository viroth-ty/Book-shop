package org.viroth.bookstore.app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*

@Serializable
data class BookInformation (
    @SerializedName("@context")
    val context: String,

    @SerializedName("@id")
    val id: String,

    @SerializedName("@type")
    val type: String,

    val isbn: String,
    val title: String,
    val description: String,
    val author: String,
    val publicationDate: String,
    val reviews: List<Review>
)
