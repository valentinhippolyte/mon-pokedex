package screens

import DefaultTextFieldPreview
import DropDownMenu
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun PokemonListScreen(navigator: Navigator, pokedex: List<Pokemon>) {
    var selectedGeneration by remember { mutableStateOf(1) }

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
            Row(
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

            pokedex.forEachIndexed { _, pokemon ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Blue)
                        .clickable { navigator.navigate(route = "/pokemon/"+ pokemon.id) }
                    ,
                ) {
                    Text(
                        text = pokemon.name + " gen " + pokemon.apiGeneration.toString(),
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
        }
    }
}
