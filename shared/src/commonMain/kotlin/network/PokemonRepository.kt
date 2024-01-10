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

    private var _pokemonByGenState = MutableStateFlow(listOf<Pokemon>())
    var pokemonByGenState = _pokemonListState

    private var _pokemonState = MutableStateFlow<Pokemon?>(null)
    var pokemonState = _pokemonState

    init {
        updatePokemonList()
    }

    suspend fun getPokemonList(): List<Pokemon> = pokemonAPI.getAllPokemons()

    private suspend fun fetchPokemonList(): List<Pokemon> = pokemonAPI.getAllPokemons()

    fun updatePokemonList(){
        coroutineScope.launch {
            _pokemonListState.update { fetchPokemonList() }
        }
    }
    private suspend fun fetchPokemonByGen(gen: Int): List<Pokemon> = pokemonAPI.getPokemonsByGen(gen)

    private fun updatePokemonByGen(gen: Int){
        coroutineScope.launch {
            _pokemonByGenState.update { fetchPokemonByGen(gen) }
        }
    }

    private suspend fun fetchOnePokemon(id: Int): Pokemon = pokemonAPI.getPokemonById(id)

    fun updateSinglePokemon(id: Int){
        coroutineScope.launch {
            _pokemonState.update { fetchOnePokemon(id) }
        }
    }
}