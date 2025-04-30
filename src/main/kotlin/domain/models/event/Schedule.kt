package domain.models.event

import kotlinx.datetime.LocalDateTime
import presentation.utils.extensions.formatToDate

data class Schedule(
    val time: LocalDateTime,
    val currentDate: String = time.formatToDate(),
    val currentHour: String = "",
)
