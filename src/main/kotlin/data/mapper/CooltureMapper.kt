package data.mapper

import dev.darkokoa.datetimewheelpicker.core.isAfter
import domain.models.event.*
import domain.util.extensions.toLocalDateTime
import kotlinx.datetime.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import presentation.utils.extensions.formatToDate
import presentation.utils.extensions.formatToTime

fun Document.toEvent(): Event {
    return Event(
        summary = this.toSummary(),
        coordinates = this.toCoordinates(),
        performances = this.toPerformances()
    )
}

private fun Document.toCoordinates(): Coordinates {

    val coordinates = coordinates()

    return Coordinates(
        latitude = coordinates["latitude"]?.convertDmsToDecimal() ?: 0.0,
        longitude = coordinates["longitude"]!!.convertDmsToDecimal() ?: 0.0
    )
}

private fun Document.coordinates() = mapOf(
    "latitude" to select("span#lat").text(),
    "longitude" to select("span#long").text()
)

private fun String.convertDmsToDecimal(): Double? {
    val regex = Regex("""(\d+)[°](\d+)'(\d+(?:\.\d+)?)?"?([NSEW])""")
    val matchResult = regex.find(this) ?: return null

    val (degrees, minutes, seconds, direction) = matchResult.destructured

    val decimal = degrees.toDouble() + (minutes.toDouble() / 60) + (seconds.toDouble() / 3600)
    return if (direction == "S" || direction == "W") -decimal else decimal
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
        schedules = getAllDatesBetween(start = dates.first, end = dates.second),
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
    fun parseDate(dateString: String): LocalDate {
        val parts = dateString.split("/")
        val day = parts[0].toInt()
        val month = parts[1].toInt()
        val year = parts[2].toInt()
        return LocalDate(year, month, day)
    }

    val dates = dateRange.split(" até ")

    val startDate = parseDate(dates[0])

    val endDate = if (dates.size != 2) {
        startDate
    } else {
        parseDate(dates[1])
    }

    return Pair(startDate, endDate)
}

private fun getAllDatesBetween(start: LocalDate, end: LocalDate): List<Schedule> {
    val dates = mutableListOf<Schedule>()
    var current = start

    while (current <= end) {

        val time = current.atStartOfDayIn(TimeZone.UTC).toLocalDateTime(TimeZone.UTC)

        dates.add(Schedule(time = time, currentHour = ""))
        current = current.plus(1, DateTimeUnit.DAY)
    }

    return dates
}

private fun Document.toTitle() = select("h1.title-single-event").text()
private fun Document.date() =  select("div.dateinfo").text()
private fun Document.place() = select("h3.place-single").text()
private fun Document.address() = select("span#address").text()
private fun Document.time() = select("div.days-single-event").text()
private fun Document.imageUrl() = select("div.image img").attr("src")



private fun Document.toPerformances(): List<Performance>{
    val performance_0 = toPerformance0()
    val performance_1 = toPerformance1()
//    val performance_2 = toPerformance2()
    val performance_3 = toPerformance3()
//    val performance_4 = toPerformance4()

    val performances = performance_0 + performance_1 /*+ performance_2 */+ performance_3 //+ performance_4

    return performances
}

private fun Document.toPerformance0(): List<Performance>{
    val performances = mutableListOf<Performance>()
    performances().forEach { it ->

        val date = it.date().toLocalDateTime()
        val currentDate = date?.formatToDate() ?: ""
        val currentHour = date?.formatToTime() ?: ""

        performances.add(
            Performance(
                artist = it.title()
                    .lowercase()
                    .split(" ")
                    .joinToString(" ") { it.replaceFirstChar { c -> c.uppercaseChar() } },
                date = it.date().toLocalDateTime(),
                imageUrl = it.imageUrl() ?: "",
                currentDate = currentDate,
                currentHour = currentHour
            )
        )
    }

    return performances
}

private fun Document.toPerformance1(): List<Performance>{
    val performances = mutableListOf<Performance>()

    val description = this.select("div.event-des").firstOrNull() ?: return emptyList()
    val dateRegex = Regex("""(\d{1,2} de [a-zA-Zçãé]+)""", RegexOption.IGNORE_CASE)
    var currentDate: String? = null

    description.select("p").forEach { paragraph ->
        val text = paragraph.text()

        val dateMatch = dateRegex.find(text)
        if (dateMatch != null) {
            currentDate = dateMatch.value
        }

        paragraph.select("strong").forEach { strong ->
            val name = strong.text()
            val imageUrl = description.select("img").firstOrNull()?.attr("src") ?: ""

            performances.add(
                Performance(
                    name = name,
                    date = currentDate?.toLocalDateTime(),
                    imageUrl = imageUrl
                )
            )
        }
    }

    return performances
}
/*
private fun Document.toPerformance2(): List<Performance>{
    val performances = mutableListOf<Performance>()

    val description = this.select("div.event-des").firstOrNull() ?: return emptyList()
    val dateRegex = Regex("""(\d{1,2} de [a-zA-Zçãé]+)""", RegexOption.IGNORE_CASE)
    var currentDate: String? = null

    description.select("p").forEach { paragraph ->
        val text = paragraph.text()

        val dateMatch = dateRegex.find(text)
        if (dateMatch != null) {
            currentDate = dateMatch.value
        }

        paragraph.select("strong").forEach { strong ->
            val name = strong.text()
            val imageUrl = description.select("img").firstOrNull()?.attr("src") ?: ""

            performances.add(
                Performance(
                    name = name,
                    date = currentDate ?: "",
                    imageUrl = imageUrl
                )
            )
        }
    }

    return performances
}
*/
private fun Document.toPerformance3(): List<Performance> {
    val performances = mutableListOf<Performance>()
    val dateElements = this.select("span[style*=color: #333399]")

    for (dateElement in dateElements) {
        val currentDate = dateElement.text()

        var nextElement: Element? = dateElement.parent()

        while (nextElement?.nextElementSibling() != null) {
            nextElement = nextElement.nextElementSibling()
            val timeElement = nextElement?.selectFirst("span[style*=color: #ff0000]")

            if (timeElement != null) {
                val time = timeElement.text()
                val text = nextElement?.text()
                val name = text?.substringAfter("– ")?.substringBefore("| Org.")?.trim()
                val location = text?.substringAfter("| ")?.substringBefore("Org.")?.trim()
                val organizer = text?.substringAfter("Org.")?.trim()

                performances.add(
                    Performance(
                        date = currentDate?.toLocalDateTime(),
                        time = time,
                        name = name ?: "",
                        location = location ?: "",
                        organizer = organizer ?: ""
                    )
                )
            }
        }
    }
    return performances
}
/*
private fun Document.toPerformance4(): List<Performance> {
    val performances = mutableListOf<Performance>()

    val columns = this.select("div.sppb-col-md-3")

    for (column in columns) {
        val content = column.select("div.sppb-addon-text-block .sppb-addon-content").firstOrNull()?.html() ?: continue

        val parts = content.split("<br>")
            .map { Jsoup.parse(it).text().trim() }
            .filter { it.isNotBlank() }

        var time = ""
        var title = ""
        var artist: String? = null
        var location = ""
        var organizer: String? = null

        for (part in parts) {
            when {
                part.matches(Regex("\\d{1,2}h\\d{2}")) -> time = part
                part.startsWith("Organização:") -> organizer = part.removePrefix("Organização:").trim()
                part.startsWith(">") -> location = part.removePrefix(">").trim()
                title.isEmpty() -> title = part
                else -> artist = part
            }
        }

        if (time.isNotEmpty() && title.isNotEmpty() && location.isNotEmpty()) {
            performances.add(
                Performance(
                    date = "",
                    time = time,
                    name = title ?: "",
                    artist = artist ?: "",
                    location = location ?: "",
                    organizer = organizer ?: ""
                )
            )
        }
    }

    return performances
}
*/
private fun Element.title() = this.text()
private fun Element.imageUrl() = this.nextElementSibling()?.selectFirst("img")?.attr("src")
private fun Element.date(): String {
    val dateElement = previousElementSibling()?.selectFirst("span")
    return dateElement?.text() ?: "Date not found"
}



fun Document.description() = select("div.event-des").text()
fun Document.category() = select("h2.category-single-event").text()
fun Document.ageLimit() = select("div.agelimit").text()
fun Document.extraInfo() = select("div.extra h4:contains(Informação Extra) + span").text().split(", ")
fun Document.performances() = select("div.event-des h3:has(span) + h2")
