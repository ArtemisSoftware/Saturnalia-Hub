package domain.models.event

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val summary: Summary,
    val coordinates: Coordinates,
    val performances: List<Performance>
)
