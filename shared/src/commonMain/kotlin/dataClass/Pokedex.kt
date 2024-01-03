package dataClass

@kotlinx.serialization.Serializable
data class Pokedex(
    var pokemons: List<Pokemon>
)