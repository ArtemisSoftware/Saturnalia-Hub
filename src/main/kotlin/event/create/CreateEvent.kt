package event.create

sealed interface CreateEvent {
    data class UpdateUrl(val url: String): CreateEvent
    data object LoadFromUrl: CreateEvent
    data class UpdateType(val id: Int): CreateEvent
    data class UpdateTitle(val title: String): CreateEvent
    data class UpdateLocation(val location: String): CreateEvent
    data class UpdateAvenue(val avenue: String): CreateEvent
    data class UpdateAddress(val address: String): CreateEvent
    data class UpdateLatitude(val latitude: String): CreateEvent
    data class UpdateLongitude(val longitude: String): CreateEvent

    sealed interface PerformaceEvent : CreateEvent {
        data class UpdateType(val index: Int, val id: Int): PerformaceEvent
        data class UpdateName(val index: Int, val name: String): PerformaceEvent
        data class UpdateArtist(val index: Int, val artist: String): PerformaceEvent
        data class UpdateLocal(val index: Int, val local: String): PerformaceEvent
        data class UpdateOrganizer(val index: Int, val organizer: String): PerformaceEvent
        data class UpdateDate(val index: Int, val date: String): PerformaceEvent
        data class UpdateHour(val index: Int, val hour: String): PerformaceEvent
        data class Delete(val index: Int): PerformaceEvent
    }

}