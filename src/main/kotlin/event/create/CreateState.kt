package event.create

import domain.models.EventType
import domain.models.event.Coordinates
import domain.models.event.Performance
import domain.models.event.PerformanceType
import domain.models.event.Summary
import presentation.composables.dropdown.DropdownItem

data class CreateState(
    val url: String = "https://www.coolture.pt/event/feira-da-luz-2024-carnide-lisboa/",
    val summary: Summary = Summary(),
    val performances: List<Performance> = emptyList(),
    val coordinates: Coordinates = Coordinates(),
    val performanceTypes: List<DropdownItem> = emptyList(),
    val eventTypes: List<DropdownItem> = emptyList()
)
