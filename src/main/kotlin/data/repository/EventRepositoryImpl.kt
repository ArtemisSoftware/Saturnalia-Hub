package data.repository

import domain.models.event.Event
import domain.repository.EventRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

class EventRepositoryImpl(): EventRepository {
    override suspend fun getEventFromJson(json: String): Event {
        val result = Json { ignoreUnknownKeys = true }.decodeFromString<Event>(json)
        return result
    }
}