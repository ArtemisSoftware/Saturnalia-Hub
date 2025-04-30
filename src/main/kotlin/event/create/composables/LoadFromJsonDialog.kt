package event.create.composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
internal fun LoadFromJsonDialog(
    url: String,
    updateJson: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Json para download")
        },
        text = {
            OutlinedTextField(
                value = url,
                label = { Text("Json") },
                onValueChange = updateJson
            )
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Download")
            }
        }
    )
}

@Preview
@Composable
private fun LoadFromJsonDialogPreview() {
    LoadFromJsonDialog(
        url = "Url",
        updateJson = {},
        onConfirm = {},
        onDismiss = {},
    )
}