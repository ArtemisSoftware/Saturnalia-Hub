package presentation.utils.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.format(format: String = "dd-MM-yyyy"): String{
    val formatter = DateTimeFormatter.ofPattern(format)
    return this.format(formatter)
}