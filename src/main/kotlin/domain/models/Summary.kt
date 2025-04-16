package domain.models

import java.time.LocalDate

data class Summary(
    val title: String = "",
    val location: String = "",
    val place: String = "",
    val address: String = "",
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
    val time: String = "",
    val imageUrl: String = "",
)
