package dataClass

data class Filters(
    val name: String? = null,
    val generation: Int? = null,
    val type: Type? = null,
) {
    fun shouldPokemonBeDisplayed(pokemon: Pokemon): Boolean =
        name?.let {
            pokemon.name.contains(name, ignoreCase = true)
        } ?: true && generation?.let {
            pokemon.generation == it
        } ?: true && type?.let {
            pokemon.types.contains(it)
        } ?: true
}
