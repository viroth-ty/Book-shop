package org.viroth.bookstore.app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*

@Serializable
data class Book (
    @SerializedName("@context")
    val context: String,

   @SerializedName("@id")
    val id: String,

   @SerializedName("@type")
    val type: String,

   @SerializedName("hydra:member")
    val hydraMember: List<HydraMember>,

   @SerializedName("hydra:totalItems")
    val hydraTotalItems: Long,

   @SerializedName("hydra:view")
    val hydraView: HydraView,

   @SerializedName("hydra:search")
    val hydraSearch: HydraSearch
)

@Serializable
data class HydraMember (
   @SerializedName("@id")
    val id: String,

   @SerializedName("@type")
    val type: String,

    val isbn: String,
    val title: String,
    val description: String,
    val author: String,
    val url: String = "",
    val publicationDate: String,
    val reviews: List<Review>
)

@Serializable
data class Review (
   @SerializedName("@id")
    val id: String,

   @SerializedName("@type")
    val type: String,

    val body: String
)

@Serializable
data class HydraSearch (
   @SerializedName("@type")
    val type: String,

   @SerializedName("hydra:template")
    val hydraTemplate: String,

   @SerializedName("hydra:variableRepresentation")
    val hydraVariableRepresentation: String,

   @SerializedName("hydra:mapping")
    val hydraMapping: List<HydraMapping>
)

@Serializable
data class HydraMapping (
   @SerializedName("@type")
    val type: String,

    val variable: String,
    val property: String? = null,
    val required: Boolean
)

@Serializable
data class HydraView (
   @SerializedName("@id")
    val id: String,

   @SerializedName("@type")
    val type: String,

   @SerializedName("hydra:first")
    val hydraFirst: String,

   @SerializedName("hydra:last")
    val hydraLast: String,

   @SerializedName("hydra:next")
    val hydraNext: String
)
