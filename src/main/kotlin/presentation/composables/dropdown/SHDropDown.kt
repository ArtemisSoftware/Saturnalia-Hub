package presentation.composables.dropdown

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SHDropDown(
    title: String,
    items: List<DropdownItem>,
    selectedValue: DropdownItem? = null,
    onUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val isDropDownExpanded = remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .wrapContentSize(Alignment.TopStart)
    ) {

        OutlinedTextField(
            value = selectedValue?.description ?: items[0].description,
            label = { Text(title) },
            enabled = false,
            onValueChange = {},
            trailingIcon = {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        isDropDownExpanded.value = true
                    }
                )
            },
        )

        DropdownMenu(
            expanded = isDropDownExpanded.value,
            onDismissRequest = { isDropDownExpanded.value = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    content = {
                        Text(text = item.description)
                    },
                    onClick = {
                        isDropDownExpanded.value = false
                        onUpdate(item.id)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SHDropDownPreview() {
    SHDropDown(
        title = "Tipo",
        items = listOf(DropdownItem(1, "Selector"), DropdownItem(2, "Selector 2")),
        onUpdate = {}
    )
}