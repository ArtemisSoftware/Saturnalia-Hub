package domain.repository

import domain.models.Event
import org.jsoup.nodes.Document

interface CooltureRepository {
    fun getEvent(url: String): Event?
}