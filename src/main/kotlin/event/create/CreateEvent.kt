package event.create

sealed interface CreateEvent {
    sealed interface SummaryEvent : CreateEvent {
        data class UpdateType(val id: Int): SummaryEvent
        data class UpdateTitle(val title: String): SummaryEvent
        data class UpdateLocation(val location: String): SummaryEvent
        data class UpdateAvenue(val avenue: String): SummaryEvent
        data class UpdateAddress(val address: String): SummaryEvent
        data class UpdateLatitude(val latitude: String): SummaryEvent
        data class UpdateLongitude(val longitude: String): SummaryEvent
        data class RemoveSchedule(val index: Int): SummaryEvent
        data class UpdateScheduleDate(val index: Int, val date: String): SummaryEvent
        data class UpdateScheduleHour(val index: Int, val hour: String): SummaryEvent
        data class UpdateImageUrl(val url: String): SummaryEvent
    }

    sealed interface CooltureEvent : CreateEvent {
        data class UpdateUrl(val url: String): CooltureEvent
        data object LoadFromUrl: CooltureEvent
    }

    sealed interface Json : CreateEvent {
        data class Update(val json: String): Json
        data object Load: Json
    }

    sealed interface PerformaceEvent : CreateEvent {
        data class UpdateType(val index: Int, val id: Int): PerformaceEvent
        data class UpdateName(val index: Int, val name: String): PerformaceEvent
        data class UpdateArtist(val index: Int, val artist: String): PerformaceEvent
        data class UpdateLocal(val index: Int, val local: String): PerformaceEvent
        data class UpdateOrganizer(val index: Int, val organizer: String): PerformaceEvent
        data class UpdateDate(val index: Int, val date: String): PerformaceEvent
        data class UpdateHour(val index: Int, val hour: String): PerformaceEvent
        data class Delete(val index: Int): PerformaceEvent
        data class UpdateImageUrl(val index: Int, val url: String): PerformaceEvent
    }

}