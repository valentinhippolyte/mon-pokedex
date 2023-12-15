import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import screens.MyPokemonScreen
import screens.PokemonListScreen
import screens.PokemonScreen
private val repository = PokemonRepository()
@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        // Fetch data Api
        val allPokemon = repository

        //if(allPokemon.v) {
            //questionScreen(questions.)
        //}

        // BottomNavBar :
        var selectedTabIndex by remember { mutableStateOf(0) }
        val tabs = listOf(
            "Pokemons" to Icons.Default.Star,
            "Capture" to Icons.Default.Favorite,
        )
        val selectedScreen = when (selectedTabIndex) {
            0 -> PokemonListScreen()
            1 -> MyPokemonScreen()
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