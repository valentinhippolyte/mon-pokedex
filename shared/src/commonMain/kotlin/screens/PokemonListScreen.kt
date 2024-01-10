package screens

import PokemonRepository
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator

private val repository = PokemonRepository()

@Composable
fun PokemonListScreen(navigator: Navigator) {
    //var selectedGeneration by remember { mutableStateOf(1) }
    val pokemonsState = repository.pokemonListState.collectAsState()
    val pokemons = pokemonsState.value

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.White
    ) {
        when {
            pokemons.isEmpty() -> {
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

                    /*Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Generation: ")
                                    DropDownMenu(
                                        selectedValue = selectedGeneration,
                                        onValueChange = { newGen ->
                                            // Call your API or perform any other action based on the selected generation
                                            selectedGeneration = newGen
                                        }
                                    )
                                }

                                DefaultTextFieldPreview()
                    */
                    //Pokemon List
                    pokemons.forEachIndexed { _, pokemon ->
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