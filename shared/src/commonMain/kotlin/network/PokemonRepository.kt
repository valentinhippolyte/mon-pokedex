import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import network.PokemonAPI

class PokemonRepository()  {

    private val pokemonAPI = PokemonAPI()
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    init {
        updatePokemon()
    }

    private suspend fun fetchPokemon(): List<Pokemon> = pokemonAPI.getAllPokemon().AllPokemon

    private fun updatePokemon(){
        coroutineScope.launch {
            fetchPokemon()
        }
    }


}