package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberImagePainter
import dataClass.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import network.repository.PokemonRepository

@Composable
fun OnePokemonScreen(navigator: Navigator, id: Int) {

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
                modifier = Modifier.fillMaxWidth().padding(top = 48.dp, start = 16.dp, bottom = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = pokemon.id.toString() + " " + pokemon.name + " (generation :" + pokemon.generation + ")",
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.width(100.dp).height(165.dp)
                    ) {
                        Image(
                            painter = rememberImagePainter(url = pokemon.image),
                            contentDescription = "Pokemon Image",
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Column (
                            modifier = Modifier
                                .height(250.dp)
                                .fillMaxWidth()
                        ) {
                            pokemon.types.forEachIndexed() { _, type ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = rememberImagePainter(url = type.image),
                                        contentDescription = "Type image",
                                        modifier = Modifier.fillMaxWidth(0.25f),
                                        contentScale = ContentScale.Fit
                                    )

                                    Spacer(modifier = Modifier.fillMaxWidth(0.05f))

                                    Text(
                                        modifier = Modifier.fillMaxWidth(0.70f),
                                        text = type.name,
                                        style = MaterialTheme.typography.body1,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }

                    // Stats
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                    ) {
//                            HP
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "HP : ",
                                    style = MaterialTheme.typography.body1
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                LinearProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth(0.80f)
                                        .height(10.dp),
                                    progress = pokemon.stats.hp.toFloat() / 200,
                                    color = Color.Green
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    text = pokemon.stats.hp.toString(),
                                    style = MaterialTheme.typography.body1,
                                    fontSize = 12.sp
                                )
                            }

//                            Attaque
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Atk :",
                                    style = MaterialTheme.typography.body1
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                LinearProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth(0.80f)
                                        .height(10.dp),
                                    progress = pokemon.stats.attack.toFloat() / 200,
                                    color = Color.Green
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    text = pokemon.stats.attack.toString(),
                                    style = MaterialTheme.typography.body1,
                                    fontSize = 12.sp
                                )
                            }

//                            Attaque Spé
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Atk Sp :",
                                    style = MaterialTheme.typography.body1
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                LinearProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth(0.80f)
                                        .height(10.dp),
                                    progress = pokemon.stats.specialAttack.toFloat() / 200,
                                    color = Color.Green
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    text = pokemon.stats.specialAttack.toString(),
                                    style = MaterialTheme.typography.body1,
                                    fontSize = 12.sp
                                )
                            }

//                            Défense
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Déf :",
                                    style = MaterialTheme.typography.body1
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                LinearProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth(0.80f)
                                        .height(10.dp),
                                    progress = pokemon.stats.defense.toFloat() / 200,
                                    color = Color.Green
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    text = pokemon.stats.defense.toString(),
                                    style = MaterialTheme.typography.body1,
                                    fontSize = 12.sp
                                )
                            }

//                            Défense Spéciale
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Déf Sp :",
                                    style = MaterialTheme.typography.body1
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                LinearProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth(0.80f)
                                        .height(10.dp),
                                    progress = pokemon.stats.specialDefense.toFloat() / 200,
                                    color = Color.Green
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    text = pokemon.stats.specialDefense.toString(),
                                    style = MaterialTheme.typography.body1,
                                    fontSize = 12.sp
                                )
                            }

//                            Vitesse
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Vitesse :",
                                    style = MaterialTheme.typography.body1
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                LinearProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth(0.70f)
                                        .height(10.dp),
                                    progress = pokemon.stats.speed.toFloat() / 200,
                                    color = Color.Green
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    text = pokemon.stats.speed.toString(),
                                    style = MaterialTheme.typography.body1,
                                    fontSize = 12.sp
                                )
                            }
                        }

                }
            }
        }
    }
}