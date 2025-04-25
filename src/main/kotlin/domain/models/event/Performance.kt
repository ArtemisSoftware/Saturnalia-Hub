package domain.models.event

import java.time.LocalDateTime

data class Performance(
    val date: LocalDateTime? = null,
    val typeId: Int? = null,
    val time: String = "",
    val name: String = "",
    val artist: String = "",
    val location: String = "",
    val imageUrl: String = "",
    val organizer: String = "",
    val currentDate: String = "",
    val currentHour: String = "",
)
