package event

import domain.models.Coordinates
import domain.models.Performance
import domain.models.Summary
import event.create.CreateState
import java.time.LocalDate

internal object PreviewData {

    val mockCoordinates = Coordinates(0.0, 0.0)
    val mockPerformance1 = Performance(
        date = "2025-07-21",
        time = "20:30",
        name = "Summer Vibes Night",
        artist = "DJ Luna",
        location = "Beach Arena, Lisbon",
        imageUrl = "https://example.com/images/dj_luna.jpg",
        organizer = "Sunset Events"
    )

    val mockPerformance2 = Performance(
        date = "2025-08-05",
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
        startDate = LocalDate.of(2025, 9, 5),
        endDate = LocalDate.of(2025, 9, 7),
        time = "10:00",
        imageUrl = "https://example.com/images/tech_sound.jpg"
    )

    val mockCreateState = CreateState(
        summary = mockSummary2,
        performances = mockPerformances,
        coordinates = mockCoordinates
    )
}