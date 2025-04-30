package domain.models.event

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(val latitude: Double = 0.0, val longitude: Double = 0.0)
