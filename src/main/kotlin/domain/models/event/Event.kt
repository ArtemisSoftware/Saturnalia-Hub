package domain.models.event

data class Event(
    val summary: Summary,
    val coordinates: Coordinates,
    val performances: List<Performance>
)
