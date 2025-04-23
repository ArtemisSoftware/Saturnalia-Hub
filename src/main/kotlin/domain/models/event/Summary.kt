package domain.models.event

import java.time.LocalDate

data class Summary(
    val id: Int = 1,
    val title: String = "",
    val location: String = "",
    val place: String = "",
    val address: String = "",
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
    val time: String = "",
    val imageUrl: String = "",
)
