package model

data class Player(
    val name: String?,
    val team: String,
    val city: String,
    val position: String,
    val nationality: String,
    val agency: String,
    val transferCost: String,
    val participations: String,
    val goals: String,
    val assists: String,
    val yellowCards: String,
    val redCards: String,
)
