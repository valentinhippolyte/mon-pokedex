package screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Button
import androidx.compose.ui.unit.dp
import dataClass.Pokemon
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun PokemonListScreen(navigator: Navigator, pokedex: List<Pokemon>) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "All the Pokemon",
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentSize()
            )
            Button(
                modifier = Modifier.padding(all = 10.dp),
                onClick = { navigator.navigate(route = "/myPokemon") }
            ) {
                Text("My pokemon screen")
            }
                pokedex.forEachIndexed { _, pokemon ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = pokemon.name,
                        style = MaterialTheme.typography.body1,
                    )
                }
            }

        }
    }
}
