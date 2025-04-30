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
    numberOfPerformances: Int,
    showDialog: () -> Unit,
    updateMenuOption: (MenuType) -> Unit,
    showJsonDialog: () -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
    ) {
        OutlinedButton(onClick = { updateMenuOption(MenuType.SUMMARY) }) {
            Text("Sumário")
        }
        OutlinedButton(onClick = { updateMenuOption(MenuType.PERFORMANCES) }) {
            if(numberOfPerformances == 0){
                Text("Performances")
            } else{
                Text("Performances: $numberOfPerformances")
            }
        }
        OutlinedButton(onClick = showDialog) {
            Text("Download evento Coolture ")
        }

        OutlinedButton(onClick = showJsonDialog) {
            Text("Download json")
        }
    }
}

@Preview
@Composable
private fun MenuPreview() {
    Menu(
        numberOfPerformances = 2,
        showDialog = {},
        showJsonDialog = {},
        updateMenuOption = {}
    )
}