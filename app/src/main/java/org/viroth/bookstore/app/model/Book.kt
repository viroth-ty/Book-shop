package org.viroth.bookstore.app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.util.Util
import retrofit2.http.Url

@Serializable
data class Book (
    @SerializedName("@context")
    val context: String,

   @SerializedName("@id")
    val id: String,

   @SerializedName("@type")
    val type: String,

   @SerializedName("hydra:member")
    val hydraMember: ArrayList<HydraMember>,

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
    val type: String?  = null,

    val isbn: String? = null,
    val title: String? = null,
    val description: String? = null,
    val author: String? = null,
    var url: Int? = null,
    val publicationDate: String? = null,
    val reviews: ArrayList<Review> = arrayListOf(),
    var isSave: Int = 0
) {
    init {
        url = R.drawable.img_placeholder
    }
}

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
    val hydraMapping: ArrayList<HydraMapping>
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
