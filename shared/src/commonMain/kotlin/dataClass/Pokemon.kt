package dataClass

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Pokemon(
    @SerialName("pokedexId") val pokedexId:Int,
    @SerialName("name") val name:String,
    @SerialName("image") val image:String,
//    val types:List<Type>,
)