package model

class Player(
    val Name: String,
    val Team: Team,
    val Position: String,
    val Nationality: String,
    val Agency: String,
    val TransferCost: Int,
    val Participations: Int,
    val Goals: Int,
    val Assists: Int,
    val YellowCards: Int,
    val RedCards: Int
) {}
