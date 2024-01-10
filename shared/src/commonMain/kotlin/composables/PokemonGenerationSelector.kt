package composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonGenerationSelector(
    selectedGeneration: Int,
    onGenerationSelected: (Int) -> Unit
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
            verticalArrangement = Arrangement.Center) {
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