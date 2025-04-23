package domain.models.event

import java.time.LocalDateTime

data class Performance(
    val date: LocalDateTime? = null,
    val time: String = "",
    val name: String = "",
    val artist: String = "",
    val location: String = "",
    val imageUrl: String = "",
    val organizer: String = "",
)
