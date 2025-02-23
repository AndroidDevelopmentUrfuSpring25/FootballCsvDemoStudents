package model

import dto.player.Position

data class Player(
    val name: String,
    val team: Team,
    val position: Position,
    val nationality: String,
    val agency: String?,
    val transferCost: Long,
    val participationCount: Int,
    val goals: Int,
    val assists: Int,
    val yellowCardsCount: Int,
    val redCardsCount: Int
)
