package model

import model.Position

class Player(
    val name: String,
    val team: Team,
    val position: Position,
    val nationality: String,
    val agency: String,
    val transferCost: Int,
    val participationCount: Int,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    val redCards: Int
)

