package composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dataClass.Type


@Composable
fun PokemonFilterForm(
    onSubmit: (String?, Int?, Type?) -> Unit
) {
    var name by remember { mutableStateOf<String?>(null) }
    var generation by remember { mutableStateOf<Int?>(null) }
    var type by remember { mutableStateOf<Type?>(null) }

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
                value = name ?: "",
                onValueChange = { value ->
                    name = value.ifBlank { null }
                },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )


            PokemonGenerationSelector(
                selectedGeneration = generation?.toString() ?: "",
                onGenerationSelected = { value -> generation = value }
            )

            PokemonTypeSelector(
                selectedType = type?.name ?: "",
                onTypeSelected = { value -> type = value }
            )

            Button(
                onClick = {
                    onSubmit(name, generation, type)
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp)
            ) {
                Text("Submit")
            }
        }
    }
}