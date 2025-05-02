package presentation.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import org.jetbrains.skia.Image
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO

object ImageUtil {

    fun loadNetworkImage(link: String): ImageBitmap {
        return try {
            val url = URL(link)
            val connection = url.openConnection() as HttpURLConnection
            connection.instanceFollowRedirects = true
            connection.setRequestProperty("User-Agent", "Mozilla/5.0")
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw IllegalArgumentException("HTTP error: $responseCode")
            }

            val contentType = connection.contentType
            if (!contentType.startsWith("image/")) {
                throw IllegalArgumentException("Invalid content type: $contentType")
            }

            val bytes = connection.inputStream.readBytes()
            if (bytes.isEmpty()) {
                throw IllegalArgumentException("Image stream is empty")
            }

            Image.makeFromEncoded(bytes).toComposeImageBitmap()
        } catch (e: Exception) {
            // Fallback to local error image
            useResource("error_image.png", ::loadImageBitmap)
        }
    }

}