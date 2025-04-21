package event.create

import domain.models.Coordinates
import domain.models.Performance
import domain.models.Summary

data class CreateState(
    val url: String = "",
    val summary: Summary = Summary(),
    val performances: List<Performance> = emptyList(),
    val coordinates: Coordinates = Coordinates()
)
