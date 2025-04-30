package domain.repository

import domain.models.event.Event

interface EventRepository {
    suspend fun getEventFromJson(json: String): Event
}