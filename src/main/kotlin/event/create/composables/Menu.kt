package event.create.composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

        Divider(
            modifier = Modifier
            .width(200.dp)
            .padding(vertical = 16.dp)
        )

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