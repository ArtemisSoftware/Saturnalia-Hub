package domain.models.event

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import presentation.utils.extensions.formatToDate

@Serializable
data class Schedule(
    val time: LocalDateTime,
    val currentDate: String = time.formatToDate(),
    val currentHour: String = "",
)
