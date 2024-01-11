package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import dataClass.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import network.repository.PokemonRepository

@Composable
fun PokemonScreen(navigator: Navigator, id: Int) {

    val pokemon: Pokemon =
        PokemonRepository.pokemonListState.collectAsState().value.single { pokemon -> pokemon.id == id }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(16.dp), elevation = 8.dp
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable { navigator.navigate(route = "/welcome") }.padding(8.dp).size(24.dp),
                )

                Icon(
                    imageVector = if (pokemon.isCaptured) Icons.Default.Favorite
                    else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    modifier = Modifier.clickable {
                            CoroutineScope(Dispatchers.Main).launch {
                                PokemonRepository.toggleCapturePokemon(pokemon)
                            }
                        }.padding(8.dp).size(24.dp),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = pokemon?.id.toString() + " " + pokemon?.name + " (generation :" + pokemon?.generation + ")",
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.width(100.dp).height(100.dp)
                    ) {
                        if (pokemon != null) {
                            Image(
                                painter = rememberImagePainter(url = pokemon.image),
                                contentDescription = "Pokemon Image",
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }

                    // Stats


                }
            }
        }
    }
}