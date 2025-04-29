package event

import domain.models.event.Coordinates
import domain.models.event.Performance
import domain.models.event.Summary
import event.create.CreateState
import kotlinx.datetime.*

internal object PreviewData {

    val mockCoordinates = Coordinates(0.0, 0.0)
    val mockPerformance1 = Performance(
        date = Clock.System.now().toLocalDateTime(TimeZone.UTC),
        time = "20:30",
        name = "Summer Vibes Night",
        artist = "DJ Luna",
        location = "Beach Arena, Lisbon",
        imageUrl = "https://example.com/images/dj_luna.jpg",
        organizer = "Sunset Events"
    )

    val mockPerformance2 = Performance(
        date = Clock.System.now().toLocalDateTime(TimeZone.UTC),
        time = "18:00",
        name = "Rock in Park",
        artist = "The Wild Ones",
        location = "Central Park Stage, Porto",
        imageUrl = "https://example.com/images/the_wild_ones.jpg",
        organizer = "Loud Sounds Co."
    )

    val mockPerformances = listOf(mockPerformance1, mockPerformance2)



    val mockSummary2 = Summary(
        title = "Tech & Sound Expo",
        location = "Porto",
        place = "Exponor Conference Center",
        address = "Rua da Feira, 4460-682 Matosinhos",
        startDate = LocalDate(2025, 9, 5),
        endDate = LocalDate(2025, 9, 7),
        time = "10:00",
        imageUrl = "https://example.com/images/tech_sound.jpg"
    )

    val mockCreateState = CreateState(
        summary = mockSummary2,
        performances = mockPerformances,
        coordinates = mockCoordinates
    )
}