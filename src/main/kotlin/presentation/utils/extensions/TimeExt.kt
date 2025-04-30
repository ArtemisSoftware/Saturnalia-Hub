package presentation.utils.extensions

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


fun LocalDate.format(format: String = "dd-MM-yyyy"): String {
    val day = this.dayOfMonth.toString().padStart(2, '0')
    val month = this.monthNumber.toString().padStart(2, '0')
    val year = this.year.toString()

    return format.replace("dd", day).replace("MM", month).replace("yyyy", year)
}

fun LocalDateTime.formatToDate(format: String = "dd-MM-yyyy"): String {
    return this.date.format(format) // Just use the date part for formatting
}

fun LocalDateTime.formatToTime(format: String = "HH:mm"): String {
    val hour = this.hour.toString().padStart(2, '0')
    val minute = this.minute.toString().padStart(2, '0')

    return format.replace("HH", hour).replace("mm", minute)
}


fun LocalDateTime.updateDate(year: Int, month: Int, day: Int): LocalDateTime =
    LocalDateTime(LocalDate(year, month, day), this.time)

fun LocalDateTime.updateHour(hour: Int, minute: Int): LocalDateTime =
    LocalDateTime(this.date, LocalTime(hour, minute))