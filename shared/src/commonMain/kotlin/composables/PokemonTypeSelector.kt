package composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun PokemonTypeSelector(
    selectedType: String,
    onTypeSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val types = listOf(
        "",
        "Normal",
        "Feu",
        "Eau",
        "Electrik",
        "Plante",
        "Poison",
        "Vol",
        "Insecte",
        "Sol",
        "Fée",
        "Combat",
        "Psy",
        "Roche",
        "Acier",
        "Dragon",
        "Ténèbres",
        "Spectre",
        "Glace"
    )

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
            types.forEach { type ->
                DropdownMenuItem(
                    onClick = {
                        onTypeSelected(type)
                        expanded = false
                    }
                ) {
                    Text(text = type)
                }
            }
        }
    }
}