import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import screens.PokemonListScreen
import screens.OnePokemonScreen


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
            PokemonListScreen(navigator, false)
        }
        scene(
            route = "/myPokemon",
            navTransition = NavTransition(),
        ) {
            PokemonListScreen(navigator, true)
        }
        scene(
            route = "/pokemon/{id}",
            navTransition = NavTransition(),
        ) { backStackEntry ->
            backStackEntry.path<Int>("id")?.let { id ->
                OnePokemonScreen(navigator, id)
            }
        }
    }
}