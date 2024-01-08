import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import screens.MyPokemonScreen
import screens.PokemonListScreen
import screens.PokemonScreen

private val repository = PokemonRepository()

@Composable
internal fun rootNavHost() {

    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = "/welcome",
    ) {
        scene(
            route = "/welcome",
            navTransition = NavTransition(),
        ) {
            val pokemons = repository.pokemonListState.collectAsState()

            if (pokemons.value.isNotEmpty()) {
                PokemonListScreen(navigator, pokemons.value)
            }
        }
        scene(
            route = "/myPokemon",
            navTransition = NavTransition(),
        ) {

            val pokemon = repository.pokemonState.collectAsState()

            MyPokemonScreen(navigator, pokemon.value)
        }
        scene(
            route = "/pokemon/{id}",
            navTransition = NavTransition(),
        ) { backStackEntry ->
            backStackEntry.path<String>("id")?.let { id ->
                PokemonScreen(navigator, id)
            }
        }
    }
}