package presentation.utils

object DateUtil {

    fun formatDate(date: String): String {
        val digits = date.filter { it.isDigit() }.take(8)

        val day = digits.take(2).toIntOrNull()?.coerceIn(1, 31)
        val month = digits.drop(2).take(2).toIntOrNull()?.coerceIn(1, 12)
        val year = digits.drop(4).take(4)

        val formatted = buildString {
            if (day != null) {
                append(day.toString().padStart(2, '0'))
                append("-")
            } else if (digits.length >= 1) {
                append(digits[0])
            }
            if (month != null) {
                append(month.toString().padStart(2, '0'))
                append("-")
            } else if (digits.length >= 3) {
                append(digits[2])
            }
            append(year)
        }

        return formatted.take(10)
    }

    fun formatTime(input: String): String {
        // Allow only digits
        val digits = input.filter { it.isDigit() }.take(4)

        val hours = digits.take(2).toIntOrNull() ?: 0
        val minutes = digits.drop(2).take(2).toIntOrNull() ?: 0

        // Validate
        val validHours = hours.coerceIn(0, 23)
        val validMinutes = minutes.coerceIn(0, 59)

        // Build final string
        return buildString {
            append(validHours.toString().padStart(2, '0'))
            append(":")
            append(validMinutes.toString().padStart(2, '0'))
        }
    }
}

