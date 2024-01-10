package dataClass

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Stats(
    @SerialName("HP") val hp: Int,
    val attack: Int,
    val defense: Int,
    @SerialName("special_attack") val specialAttack: Int,
    @SerialName("special_defense") val specialDefense: Int,
    val speed: Int,
)
