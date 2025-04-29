package domain.util.extensions

import kotlinx.datetime.*
import java.time.format.TextStyle
import java.util.Locale


fun String.toLocalDateTime(): LocalDateTime? {
    val locale = Locale("pt", "PT")

    // Step 1: Split parts
    val parts = this.split(",")
    if (parts.size < 2) return null

    val datePart = parts[0].trim() // "31 de agosto"
    val timePart = parts.getOrNull(2)?.trim() ?: parts[1].trim() // "21:30"

    // Step 2: Extract day and month
    val dateRegex = Regex("""(\d{1,2}) de (\w+)""")
    val match = dateRegex.find(datePart) ?: return null
    val day = match.groupValues[1].toInt()
    val monthName = match.groupValues[2].lowercase(locale)

    // Step 3: Convert month name to number
    val month = Month.values().firstOrNull {
        it.getDisplayName(TextStyle.FULL, locale).lowercase(locale) == monthName
    } ?: return null

    // Step 4: Parse time
    val time = LocalTime.parse(timePart)

    // Step 5: Create LocalDateTime
    val year = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year // or any logic to determine year
    return LocalDateTime(year = year, month = month, dayOfMonth  = day, hour = time.hour, minute = time.minute)
}
