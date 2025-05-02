package event.create.composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.utils.ImageContainer

@Composable
internal fun Banner(
    imageUrl: String,
    updateImageUrl: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Banner",
            fontSize = 24.sp
        )

        if(imageUrl.isNotEmpty() && !imageUrl.isNullOrEmpty()) {
            Image(
                modifier = Modifier.fillMaxWidth().weight(0.5F),
                bitmap = ImageContainer.getImage(imageUrl),
                contentDescription = ""
            )
        }
        OutlinedTextField(
            value = imageUrl,
            label = { Text("Url") },
            onValueChange = updateImageUrl,
        )
    }
}

@Preview
@Composable
private fun BannerPreview() {
    Banner(
        imageUrl = "",
        updateImageUrl = {},
    )
}