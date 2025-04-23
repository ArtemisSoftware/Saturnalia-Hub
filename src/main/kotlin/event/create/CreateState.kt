package event.create

import domain.models.event.Coordinates
import domain.models.event.Performance
import domain.models.event.Summary

data class CreateState(
    val url: String = "",
    val summary: Summary = Summary(),
    val performances: List<Performance> = emptyList(),
    val coordinates: Coordinates = Coordinates()
)
