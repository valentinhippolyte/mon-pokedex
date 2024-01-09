import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Enter text"
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        label = { Text(placeholder) }, // Utiliser Text() pour entourer le texte du label
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        )
    )
}

@Composable
fun DefaultTextFieldPreview() {
    var inputValue by remember { mutableStateOf("") }
    DefaultTextField(
        value = inputValue,
        onValueChange = {
            inputValue = it
            // TODO: Vous pouvez ajouter ici tout code supplémentaire pour gérer le changement de valeur
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = "Enter a pokemon name"
    )
}
