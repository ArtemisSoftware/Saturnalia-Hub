package event.create.composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
internal fun LoadFromCooltureUrlDialog(
    url: String,
    updateUrl: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Url para download")
        },
        text = {
            OutlinedTextField(
                value = url,
                label = { Text("Coolture Url") },
                onValueChange = updateUrl
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
private fun LoadFromCooltureUrlDialogPreview() {
    LoadFromCooltureUrlDialog(
        url = "Url",
        updateUrl = {},
        onConfirm = {},
        onDismiss = {},
    )
}