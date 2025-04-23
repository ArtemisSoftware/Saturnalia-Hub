package domain.models.event

data class Performance(
    val date: String = "",
    val time: String = "",
    val name: String = "",
    val artist: String = "",
    val location: String = "",
    val imageUrl: String = "",
    val organizer: String = "",
)
