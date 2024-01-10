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
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi


private val repository = PokemonRepository()

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val navigator = rememberNavigator()
    MaterialTheme {
        //appel api
        val pokemons = repository.pokemonListState.collectAsState()
        val pokemon = repository.pokemonState.collectAsState()

        rootNavHost()
//        // BottomNavBar :
        var selectedTabIndex by remember { mutableStateOf(0) }
        val tabs = listOf(
            "Pokemons" to Icons.Default.Star,
            "Capture" to Icons.Default.Favorite,
        )
        val selectedScreen = when (selectedTabIndex) {
            0 -> { navigator.navigate(route = "/welcome") }
            1 -> { navigator.navigate(route = "/myPokemon") }
            else -> throw IllegalArgumentException("Unknown tab index: $selectedTabIndex")
        }

        Column {
            // screen content :
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                selectedScreen
            }

            BottomNavigation(
            ) {
                tabs.forEachIndexed { index, (title, icon) ->
                    BottomNavigationItem(
                        icon = { Icon(icon, contentDescription = null) },
                        label = { Text(text = title) },
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                        }
                    )
                }
            }
        }
    }
}
expect fun getPlatformName(): String