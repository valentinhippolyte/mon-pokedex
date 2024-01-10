package dataClass

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val image: String,
    @SerialName("apiGeneration") val generation: Int,
    val stats: Stats,
    @SerialName("apiTypes") val types: List<Type>,
)
