package screens

import PokemonRepository
import androidx.compose.foundation.border
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
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import composables.PokemonGenerationSelector
import composables.PokemonTypeSelector
import dataClass.Pokedex
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val repository = PokemonRepository()
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun PokemonListScreen(navigator: Navigator, pokedex: List<Pokemon>) {
    var filterGeneration by remember { mutableStateOf(1) }
    var filterName by remember { mutableStateOf("") }
    var filterType by remember { mutableStateOf("") }

    LaunchedEffect(filterName, filterGeneration, filterType) {
        loadData(filterName, filterGeneration, filterType)
    }

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

                PokemonFilterForm(
                    filterName = filterName,
                    onFilterNameChange = { filterName = it },
                    filterGeneration = filterGeneration,
                    onFilterGenerationChange = { filterGeneration = it },
                    filterType = filterType,
                    onFilterTypeChange = { filterType = it },
                    onSubmit = {
                        GlobalScope.launch {
                            loadData(filterName, filterGeneration, filterType)
                        }
                    }
                )
            }

            pokedex.forEachIndexed { _, pokemon ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { navigator.navigate(route = "/pokemon/"+ pokemon.id) }
                        .padding(2.dp)
                        .border(1.dp, Color.Black)
                    ,
                ) {
                    Text(
                        text = pokemon.name + " gen " + pokemon.generation.toString(),
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
        }
    }
}

@Composable
fun PokemonFilterForm(
    filterName: String,
    onFilterNameChange: (String) -> Unit,
    filterGeneration: Int,
    onFilterGenerationChange: (Int) -> Unit,
    filterType: String,
    onFilterTypeChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            TextField(
                value = filterName,
                onValueChange = { onFilterNameChange(it) },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )


                PokemonGenerationSelector(
                    selectedGeneration = filterGeneration,
                    onGenerationSelected = { onFilterGenerationChange(it) }
                )

                PokemonTypeSelector(
                    selectedType = filterType,
                    onTypeSelected = { onFilterTypeChange(it) }
                )

            Button(
                onClick = onSubmit,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp)
            ) {
                Text("Submit")
            }
        }
    }
}

suspend fun loadData(name: String, generation: Int, type: String): Pokedex {
    return withContext(Dispatchers.IO) {
        val filteredPokemonList = mutableListOf<Pokemon>()
        val pokedex = Pokedex(repository.getPokemonList())

        pokedex.pokemons.forEachIndexed pokemonLoop@{ _, pokemon ->
            if (name != "" && !pokemon.name.contains(name, ignoreCase = true)){
                return@pokemonLoop
            }

            if (generation != 0 && pokemon.generation != generation){
                return@pokemonLoop
            }

            pokemon.types.forEachIndexed { _, pokemonType ->
                if (type != "" && pokemonType.name != type){
                    return@pokemonLoop
                }
            }

            filteredPokemonList.add(pokemon)
        }

        Pokedex(filteredPokemonList)
    }
}
