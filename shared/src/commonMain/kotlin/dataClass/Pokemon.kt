package dataClass

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val image: String,
)