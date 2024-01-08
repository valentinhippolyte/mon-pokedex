package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dataClass.Pokemon
import moe.tlaster.precompose.navigation.Navigator
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState

@Composable
fun PokemonListScreen(navigator: Navigator, pokedex: List<Pokemon>) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                text = "Pokedex",
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentSize()
            )

            pokedex.forEachIndexed { _, pokemon ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Blue)
                        .clickable { navigator.navigate(route = "/pokemon/"+ pokemon.id) }
                    ,
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
