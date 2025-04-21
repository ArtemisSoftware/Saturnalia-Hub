package data.mapper

import domain.models.Event
import domain.models.Summary
import org.jsoup.nodes.Document
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun Document.toEvent(): Event{
    return Event(
        summary = this.toSummary()
    )
}

private fun Document.toSummary(): Summary {

    val titleAndLocation = extractTitleAndLocation(input = this.toTitle())
    val dates = convertToDates(dateRange = this.date())
    val address = this.address()

    return Summary(
        title = titleAndLocation.first,
        location = titleAndLocation.second,
        place = getPlace(this.place(), address),
        address = address,
        startDate = dates.first,
        endDate = dates.second,
        time = this.time(),
        imageUrl = this.imageUrl()
    )
}

private fun getPlace(place: String, address: String): String {
    return if(place == address){
        ""
    } else {
        val parts = place.split(",")
        parts.first()
    }
}

private fun extractTitleAndLocation(input: String): Pair<String, String> {
    fun capitalize(text: String): String {
        val lowercaseWords = setOf("de", "do", "da", "dos", "das", "e")
        return text.split(" ").joinToString(" ") { word ->
            if (word in lowercaseWords) word else word.replaceFirstChar { it.uppercase() }
        }
    }

    val parts = input.split("|")

    val title = capitalize(parts[0].trim().lowercase())
    val location = if (parts.size < 2){
        ""
    } else {
        capitalize(parts[1].trim().lowercase())
    }

    return Pair(title, location)
}

private fun convertToDates(dateRange: String): Pair<LocalDate, LocalDate> {
    // Define the date format
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    // Split the input string to get start and end dates
    val dates = dateRange.split(" até ")

    val startDate = LocalDate.parse(dates[0], formatter)

    val endDate = if (dates.size != 2){
        startDate
    } else {
        LocalDate.parse(dates[1], formatter)
    }

    return Pair(startDate, endDate)
}

private fun Document.toTitle() = select("h1.title-single-event").text()
private fun Document.date() =  select("div.dateinfo").text()
private fun Document.place() = select("h3.place-single").text()
private fun Document.address() = select("span#address").text()
private fun Document.time() = select("div.days-single-event").text()
private fun Document.imageUrl() = select("div.image img").attr("src")


fun Document.description() = select("div.event-des").text()
fun Document.coordinates() = mapOf(
    "latitude" to select("span#lat").text(),
    "longitude" to select("span#long").text()
)
fun Document.category() = select("h2.category-single-event").text()
fun Document.ageLimit() = select("div.agelimit").text()
fun Document.extraInfo() = select("div.extra h4:contains(Informação Extra) + span").text().split(", ")
fun Document.performances() = select("div.event-des h3:has(span) + h2")
