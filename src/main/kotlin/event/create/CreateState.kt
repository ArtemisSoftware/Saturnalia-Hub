package event.create

import domain.models.EventType
import domain.models.event.Coordinates
import domain.models.event.Performance
import domain.models.event.PerformanceType
import domain.models.event.Summary
import presentation.composables.dropdown.DropdownItem

val urls = listOf(
//        "https://www.coolture.pt/event/feira-de-sao-marcos-2025/",
//        "https://www.coolture.pt/event/feira-da-luz-2024-carnide-lisboa/",  //os artistas não estão corretamente formatados
//    "https://www.coolture.pt/event/carnaval-de-sines-2025/", //os eventos não estão corretamente formatados
//        "https://www.coolture.pt/event/feira-de-todos-os-santos-2024-cartaxo/",
//        "https://www.coolture.pt/event/feira-nova-de-santa-iria-2024-ourem/",
//        "https://www.coolture.pt/event/feira-de-marco-2025-aveiro/",
        "https://www.coolture.pt/event/festival-nacional-de-gastronomia-2024-santarem/",
)

data class CreateState(
    val url: String = urls[0],
    val summary: Summary = Summary(),
    val performances: List<Performance> = emptyList(),
    val coordinates: Coordinates = Coordinates(),
    val performanceTypes: List<DropdownItem> = emptyList(),
    val eventTypes: List<DropdownItem> = emptyList(),
    val currentSchedules: List<DropdownItem> = emptyList()
)
