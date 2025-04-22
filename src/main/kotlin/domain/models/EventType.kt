package domain.models

data class EventType(val id: Int, val description: String){
    companion object {
        private val festival = EventType(id = 1, description = "Festival")
        private val carnival = EventType(id = 2, description = "Feira")
        private val carnaval = EventType(id = 3, description = "Carnaval")

        val eventTypes = listOf(festival, carnival, carnaval)
    }
}