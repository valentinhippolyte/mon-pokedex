package network.repository

import dataClass.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import network.api.PokemonAPI

object PokemonRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var _pokemonListState: MutableStateFlow<List<Pokemon>> = MutableStateFlow(listOf())
    val pokemonListState: StateFlow<List<Pokemon>> = _pokemonListState.asStateFlow()

    private suspend fun fetchPokemonList(): List<Pokemon> = PokemonAPI.getAllPokemons()

    suspend fun initPokemonList() {
        coroutineScope.launch {
            _pokemonListState.update {
                fetchPokemonList()
            }
        }
    }

    suspend fun toggleCapturePokemon(pokemon: Pokemon) {
        val newPokedexState = pokemonListState.value.filter {
            it.id != pokemon.id  // On garde tous les autres pokemons
        }.toMutableList()
        newPokedexState.add(pokemon.copy(isCaptured = !pokemon.isCaptured))
        _pokemonListState.value = newPokedexState.sortedBy { it.id }
    }
}