import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import dataClass.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import network.PokemonAPI

class PokemonRepository()  {

    private val pokemonAPI = PokemonAPI()
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private var _pokemonListState = MutableStateFlow(listOf<Pokemon>())
    var pokemonListState = _pokemonListState

    private var _pokemonState = MutableStateFlow<Pokemon?>(null)
    var pokemonState = _pokemonState

    init {
        updatePokemonList()
        updateSinglePokemon()
    }

    private suspend fun fetchPokemonList(): List<Pokemon> = pokemonAPI.getAllPokemons()

    private fun updatePokemonList(){
        coroutineScope.launch {
            _pokemonListState.update { fetchPokemonList() }
        }
    }

    private suspend fun fetchOnePokemon(): Pokemon = pokemonAPI.getPokemonById()

    private fun updateSinglePokemon(){
        coroutineScope.launch {
            _pokemonState.update { fetchOnePokemon() }
        }
    }


//    private suspend fun fetchPokemonList(): List<Pokemon> = pokemonAPI.getAllPokemons().pokemons
//
//    private suspend fun fetchSinglePokemon(): Pokemon = pokemonAPI.getPokemonById()
//
//    private fun updatePokemonList() {
//        coroutineScope.launch {
//            _pokemonListState.update { fetchPokemonList() }
//        }
//    }
//
//    private fun updateSinglePokemon() {
//        coroutineScope.launch {
//            _singlePokemonState.update { fetchSinglePokemon() }
//        }
//    }
}