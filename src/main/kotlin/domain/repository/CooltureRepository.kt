package domain.repository

import domain.models.event.Event

interface CooltureRepository {
    suspend fun getEvent(url: String): Event?
}