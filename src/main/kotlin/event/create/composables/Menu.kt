package event.create.composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import event.create.model.MenuType

@Composable
internal fun Menu(
    showDialog: () -> Unit,
    updateMenuOption: (MenuType) -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
    ) {
        OutlinedButton(onClick = { updateMenuOption(MenuType.SUMMARY) }) {
            Text("Sumário")
        }
        OutlinedButton(onClick = { updateMenuOption(MenuType.PERFORMANCES) }) {
            Text("Performances")
        }
        OutlinedButton(onClick = showDialog) {
            Text("Download Coolture evento")
        }
    }
}

@Preview
@Composable
private fun MenuPreview() {
    Menu(
        showDialog = {},
        updateMenuOption = {}
    )
}