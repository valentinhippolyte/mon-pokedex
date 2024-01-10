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
                PokemonListScreen(navigator)
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
            backStackEntry.path<Int>("id")?.let { id ->
                PokemonScreen(navigator, id)
            }
        }
    }
}