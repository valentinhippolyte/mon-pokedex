import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun DropDownMenu(
    selectedValue: Int,
    onValueChange: (Int) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(200.dp)
            .clickable { expanded = true },
    ) {
        BasicTextField(
            value = selectedValue.toString(),
            onValueChange = {
                onValueChange(it.toIntOrNull() ?: selectedValue)
            },
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .clickable { expanded = true }
        )
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop down arrow")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            val coroutineScope = rememberCoroutineScope()

            for (i in 1..8) {
                DropdownMenuItem(onClick = {
                    coroutineScope.launch {
                        onValueChange(i)
                        expanded = false
                    }
                }) {
                    Text(text = "Gen $i")
                }
            }
        }
    }
}
