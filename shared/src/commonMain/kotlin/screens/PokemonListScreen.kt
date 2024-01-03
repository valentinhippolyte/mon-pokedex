package screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dataClass.Pokedex

@Composable
fun PokemonListScreen(pokedex: Pokedex) {
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


                pokedex.pokemons.forEachIndexed { _, pokemon ->
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
