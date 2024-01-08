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
import dataClass.Pokemon
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun MyPokemonScreen(navigator: Navigator, pokemon: Pokemon?) {
    var pokemonName = ""
    if (pokemon != null) {
        pokemonName = pokemon.name
    }
    else{
        pokemonName = "toto"
    }

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
                text = pokemonName,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentSize()
            )
        }
    }
}