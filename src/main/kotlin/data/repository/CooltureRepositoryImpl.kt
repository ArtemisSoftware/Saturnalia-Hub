package data.repository

import data.mapper.toEvent
import domain.models.Event
import domain.repository.CooltureRepository
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class CooltureRepositoryImpl: CooltureRepository {

    override fun getEvent(url: String): Event? {
        return try {
            val divContent = Jsoup.connect(url).get().select("div.col-lg-8").html()
            Jsoup.parse(divContent).toEvent()
        } catch (e: Exception) {
            println("Error fetching event data: ${e.message}")
            null
        }
    }
}