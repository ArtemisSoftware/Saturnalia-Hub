package event.create.mapper

import domain.models.EventType
import domain.models.event.PerformanceType
import presentation.composables.dropdown.DropdownItem

internal fun PerformanceType.toDropDownItem(): DropdownItem{
    return DropdownItem(
        id = id,
        description = description
    )
}

internal fun EventType.toDropDownItem(): DropdownItem{
    return DropdownItem(
        id = id,
        description = description
    )
}