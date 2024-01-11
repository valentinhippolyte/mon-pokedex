package composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonGenerationSelector(
    selectedGeneration: String,
    onGenerationSelected: (Int?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val generations = (1..8).toList()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .width(150.dp)
                .height(50.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Generation: $selectedGeneration")
        }

        IconButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.padding(start = 150.dp)
        ) {
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                modifier = Modifier.width(150.dp),
                onClick = {
                    onGenerationSelected(null)
                    expanded = false
                }
            ) {
                Text(text = "Select a generation")
            }
            generations.forEach { generation ->
                DropdownMenuItem(
                    modifier = Modifier.width(150.dp),
                    onClick = {
                        onGenerationSelected(generation)
                        expanded = false
                    }
                ) {
                    Text(text = "Generation $generation")
                }
            }
        }
    }
}