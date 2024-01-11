package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composables.PokemonFilterForm
import dataClass.Filters
import dataClass.Pokemon
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator
import network.repository.PokemonRepository

@Composable
fun PokemonListScreen(navigator: Navigator, displayOnlyCapturedPokemon: Boolean) {
    var filters by remember { mutableStateOf(Filters()) }
    val pokedex by PokemonRepository.pokemonListState.collectAsStateWithLifecycle(listOf())
    var filteredPokedex by remember { mutableStateOf(listOf<Pokemon>()) }

    LaunchedEffect(filters, pokedex) {
        filteredPokedex = pokedex.filter { pokemon ->
            filters.shouldPokemonBeDisplayed(pokemon)
        }.filter { pokemon ->
            !displayOnlyCapturedPokemon || pokemon.isCaptured
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                PokemonFilterForm(
                    onSubmit = { name, generation, type ->
                        filters = Filters(name, generation, type)
                    }
                )
            }

            when {
                filteredPokedex.isEmpty() -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }

                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                    ) {
                        //Screen Title
                        Text(
                            text = "MyPokedex",
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier
                                .padding(8.dp)
                                .wrapContentSize()
                                .align(alignment = Alignment.CenterHorizontally)
                        )

                        //Pokemon List
                        filteredPokedex.forEachIndexed { _, pokemon ->
                            //Pokemon Card
                            Card(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable { navigator.navigate(route = "/pokemon/" + pokemon.id) }
                                    .background(
                                        color = Color.Transparent,
                                        shape = MaterialTheme.shapes.medium
                                    )
                                    .padding(5.dp),
                            ) {
                                //Content
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(2.dp)
                                ) {
                                    Text(
                                        text = pokemon.id.toString() + " " + pokemon.name,
                                        style = MaterialTheme.typography.body1,
                                    )
                                }
                            }

                            Spacer(
                                modifier = Modifier
                                    .height(5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}