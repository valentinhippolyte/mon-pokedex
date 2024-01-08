import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dataClass.Pokedex
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screens.MyPokemonScreen
import screens.PokemonListScreen
import screens.PokemonScreen

private val repository = PokemonRepository()

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        //appel api
        val pokemons = repository.pokemonListState.collectAsState()
        val pokemon = repository.pokemonState.collectAsState()

        rootNavHost()
//        // BottomNavBar :
//        var selectedTabIndex by remember { mutableStateOf(1) }
//        val tabs = listOf(
//            "Pokemons" to Icons.Default.Star,
//            "Capture" to Icons.Default.Favorite,
//            "OnePokemon" to Icons.Default.Favorite,
//        )
//        val selectedScreen = when (selectedTabIndex) {
//            0 -> PokemonListScreen(navigator, Pokedex(pokemons.value))
//            1 -> MyPokemonScreen(navigator, pokemon.value)
//            2 -> PokemonScreen(navigator)
//            else -> throw IllegalArgumentException("Unknown tab index: $selectedTabIndex")
//        }
//
//        Column {
//            // screen content :
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxSize()
//            ) {
//                selectedScreen
//            }
//
//            BottomNavigation(
//            ) {
//                tabs.forEachIndexed { index, (title, icon) ->
//                    BottomNavigationItem(
//                        icon = { Icon(icon, contentDescription = null) },
//                        label = { Text(text = title) },
//                        selected = selectedTabIndex == index,
//                        onClick = {
//                            selectedTabIndex = index
//                        }
//                    )
//                }
//
//            }
//        }
    }
}
expect fun getPlatformName(): String