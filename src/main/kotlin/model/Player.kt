package model

data class Player(
    val name: String,
    val team: Team,
    val position: String,
    val nationality: String,
    val agency: String?,      // Может быть null или пустой строкой, если агентство не указано
    val transferCost: Int,
    val participations: Int,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    val redCards: Int
)
