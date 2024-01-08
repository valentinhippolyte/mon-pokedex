package screens

import PokemonRepository
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dataClass.Pokemon
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.delay
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlin.coroutines.coroutineContext
import kotlin.native.concurrent.ThreadLocal

private val repository = PokemonRepository()

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PokemonScreen(navigator: Navigator, id: Int) {
//    repository.updateSinglePokemon(id)

    val pokemon = repository.pokemonState.collectAsState().value

    LaunchedEffect(id) {
        repository.updateSinglePokemon(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick =  { navigator.navigate(route = "/welcome") },
        ) {
            Text(
                text = "retour",
                style = MaterialTheme.typography.h6
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
//                AsyncImage(
//                    model = "https://delasign.com/delasignBlack.png",
//                    placeholder = painterResource(id = R.drawable.sudoimage),
//                    error = painterResource(id = R.drawable.sudoimage),
//                    contentDescription = "The delasign logo",
//                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = pokemon?.id.toString() + " " + pokemon?.name,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}