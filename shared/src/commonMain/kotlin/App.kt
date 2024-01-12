import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import moe.tlaster.precompose.navigation.rememberNavigator
import network.repository.PokemonRepository
import network.repository.PokemonTypeRepository
import org.jetbrains.compose.resources.ExperimentalResourceApi


@Composable
fun App() {
    val navigator = rememberNavigator()

    // Initialisation des pokemons et des types*
    LaunchedEffect(Unit) {
        PokemonTypeRepository.initPokemonTypes()
        PokemonRepository.initPokemonList()
    }

    MaterialTheme {
        rootNavHost()
        var selectedTabIndex by remember { mutableStateOf(0) }
        val tabs = listOf(
            "Pokemons" to Icons.Default.Star,
            "Capture" to Icons.Default.Favorite,
        )
        val selectedScreen = when (selectedTabIndex) {
            0 -> {
                navigator.navigate(route = "/welcome")
            }

            1 -> {
                navigator.navigate(route = "/myPokemon")
            }

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
                backgroundColor = Color.Red
            ) {
                tabs.forEachIndexed { index, (title, icon) ->
                    BottomNavigationItem(
                        icon = { Icon(icon, contentDescription = null, tint = Color.White) },
                        label = { Text(text = title, color = Color.White) },
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