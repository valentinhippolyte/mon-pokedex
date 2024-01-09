package dataClass


data class Pokemon(
    val id: Int,
    val name: String,
    val image: String,
    val stats: Stats,
    val types: List<Type>,
)
