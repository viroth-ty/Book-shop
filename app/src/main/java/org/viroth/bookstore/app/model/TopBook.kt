package org.viroth.bookstore.app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*

@Serializable
data class TopBook (
    @SerializedName("@context")
    val context: String,

    @SerializedName("@id")
    val id: String,

    @SerializedName("@type")
    val type: String,

    @SerializedName("hydra:member")
    val hydraMember: ArrayList<TopBookHydraMember>,

    @SerializedName("hydra:totalItems")
    val hydraTotalItems: Long
)

@Serializable
data class TopBookHydraMember (
    @SerializedName("@id")
    val id: String,

    @SerializedName("@type")
    val type: String,

    @SerializedName("id")
    val hydraMemberID: Int,

    val title: String,
    val author: String,
    val part: String,
    val place: String,
    val borrowCount: Long
)
