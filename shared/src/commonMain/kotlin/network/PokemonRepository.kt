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

    private var _pokemonState=  MutableStateFlow(listOf<Pokemon>())
    var pokemonState = _pokemonState

    init {
        updatePokemon()
    }

    private suspend fun fetchPokemon(): List<Pokemon> = pokemonAPI.getAllPokemons().pokemons

    private fun updatePokemon(){
        coroutineScope.launch {
            _pokemonState.update { fetchPokemon() }
        }
    }
}
//class QuizRepository()  {
//
//    private val quizAPI = QuizAPI()
//    private val coroutineScope = CoroutineScope(Dispatchers.Default)
//
//    private var _questionState=  MutableStateFlow(listOf<Question>())
//    var questionState = _questionState
//
//    init {
//        updateQuiz()
//    }
//
//    private suspend fun fetchQuiz(): List<Question> = quizAPI.getAllQuestions().questions
//
//    private fun updateQuiz(){
//
//        coroutineScope.launch {
//            _questionState.update { fetchQuiz() }
//        }
//    }
//}