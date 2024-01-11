package composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dataClass.Type
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import network.repository.PokemonTypeRepository

@Composable
fun PokemonTypeSelector(
    selectedType: String,
    onTypeSelected: (Type?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val types by PokemonTypeRepository.typeState.collectAsStateWithLifecycle(listOf())

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text("Type: $selectedType")

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
                onClick = {
                    onTypeSelected(null)
                    expanded = false
                }
            ) {
                Text(text = "Select a type")
            }
            types.forEach { type ->
                DropdownMenuItem(
                    onClick = {
                        onTypeSelected(type)
                        expanded = false
                    }
                ) {
                    Text(text = type.name)
                }
            }
        }
    }
}