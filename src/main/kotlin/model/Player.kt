package model

data class Player(
    val name: String,
    val team: Team,
    val position: Position,
    val nationality: String,
    val agency: String,
    val transfer: Int,
    val participation: Int,
    val goals: Int,
    val assists: Int,
    val yellow: Int,
    val red: Int,
)