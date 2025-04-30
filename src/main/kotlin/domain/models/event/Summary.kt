package domain.models.event

import domain.models.EventType
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Summary(
    val typeId: Int = EventType.eventTypes.first().id,
    val title: String = "",
    val location: String = "",
    val place: String = "",
    val address: String = "",
    val startDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    val endDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    val schedules: List<Schedule> = emptyList(),
    val time: String = "",
    val imageUrl: String = "",
)
