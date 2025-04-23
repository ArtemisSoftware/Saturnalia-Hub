package data.repository

import data.mapper.toEvent
import domain.models.event.Event
import domain.repository.CooltureRepository
import kotlinx.coroutines.Dispatchers
import org.jsoup.Jsoup

class CooltureRepositoryImpl: CooltureRepository {

    override suspend fun getEvent(url: String): Event? = with(Dispatchers.IO) {
        return try {
            val divContent = Jsoup.connect(url).get().select("div.col-lg-8").html()
            Jsoup.parse(divContent).toEvent()
        } catch (e: Exception) {
            println("Error fetching event data: ${e.message}")
            null
        }
    }
}