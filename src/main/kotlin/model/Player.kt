package model

data class Player(
    val name: String,
    val team: Team,
    val nationality: String,
    val position: PlayerPosition,  // BURADA STRİNG DEĞİL ENUM KULLANILMALI
    val transferValue: Double,
    val goals: Int,
    val redCards: Int,
    val matchesPlayed: Int,
    val assists: Int,
    val yellowCards: Int,
    val agency: String?
)
