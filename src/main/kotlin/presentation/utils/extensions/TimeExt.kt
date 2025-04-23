package presentation.utils.extensions

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDate.format(format: String = "dd-MM-yyyy"): String{
    val formatter = DateTimeFormatter.ofPattern(format)
    return this.format(formatter)
}

fun LocalDateTime.formatToDate(format: String = "dd-MM-yyyy"): String{
    val formatter = DateTimeFormatter.ofPattern(format)
    return this.toLocalDate().format(formatter)
}

fun LocalDateTime.formatToTime(format: String = "HH:mm"): String{
    val formatter = DateTimeFormatter.ofPattern(format)
    return this.toLocalTime().format(formatter)
}